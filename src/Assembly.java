
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Assembly{

	private String[] instructions;
	private HashMap<String, Integer> labels;
	private Integer[] binaryInstructions;
	private int i = 0;
	private Databank db;
	private int lineCounter = 0;
	private int[] lineLookup = new int[0x3FFF];
	public String CodeError = "";


    /*@brief Initializes Assembly class, purpose is to hold instruction and
    * their corresponding labels.
	*
	* @param String[] ins all instructions, HashMap<String, Integer> ls labels
	* as keys and indexes of labels as values.
	* @return None.
	*/
	public Assembly(String[] ins, HashMap<String, Integer> ls){
		instructions = ins;
		labels = ls;
		db = new Databank();
		CPU.totalBytes = -1;
	}

	public int getLineLookup(int index){
	    int tmp = 0;
        if(index == 0){
            tmp = lineLookup[index] + 1;
        } else {
            tmp = lineLookup[index-1] + 1;
        }
        return tmp;
    }

    /*@brief Gets the array of instructions when called. Array is size
    * Global.MAX_SIZE/8
	*
	* @param None.
	* @return String[] instructions
	*/
	public String[] getAllInstructions(){
		return instructions;
	}

    /*@brief Gets the HashMap of labels and their indexes.
	*
	* @param None.
	* @return HashMap<String, Integer> labels
	*/
	public HashMap<String, Integer> getAllLabels(){
		return labels;
	}

    /*@brief Gets a single instruction from array
	*
	* @param int i index of instruction you would like
	* @return String instructions[i]
	*/
	public String getInstruction(int i){
		return instructions[i];
	}

    /*@brief Gets a single corresponding index to label passed.
	*
	* @param String key label
	* @return int index of label
	*/
	public int getLabelIndex(String key){
		return labels.get(key);
	}

	/*@brief converts instructions to opcodes and send them to memory 
	*
	* @param None
	* @return None.
	*/
	public Integer[] assemble(){
		binaryInstructions = new Integer[Global.MAX_MEMORY];
		i = 0;
		for (String instruction  : instructions) {
			if (instruction == null || instruction.equals("done")){
				continue;
			}
			if (instruction.equals("LABEL")) {
				//binaryInstructions[i++] = 0xEA ; //NOP
				i++;
				continue;
			}
			//System.out.println(instruction);
			setupQueue(instruction);
			lineCounter++;
		}
		
		Integer[] result = Arrays.copyOfRange(binaryInstructions, 0, i);
		return result;
		}


	private void addBytes(int opcode){
		CPU.totalBytes += db.getJumpCode(opcode);
		lineLookup[lineCounter] = CPU.totalBytes;
	}


	private int setupQueue(String instruction){
		int modebit = 0;
			// (1) Implicit / (2) Accumulator / (3) Immediate / (4) Zero Page / (5) Zero Page,X / (6) Zero Page,Y / (7) branch
			// (8) Absolute / (9) Absolute,X / (10) Absolute,Y / (11) Indirect / (12) (Indirect,X) / (13) (Indirect,Y)
		// have to run Immediate check before before ZeroPage
		instruction = instruction.trim();
		instruction = instruction.replaceAll("\\s+", " ");
		if (instruction.split(" ")[0].length() != 3 || instruction.trim().charAt(instruction.length()-1) == ':'|| instruction.isEmpty()){
            CodeError = instruction;
			throw new java.lang.NullPointerException();
		}
		String instName = instruction.substring(0, 3);
		String[] params = instruction.split("\\s+");
	    if (checkImplicit(instruction)){
	    	modebit = 1;
	    	try{
                int opcode= db.getOPCode(instName, modebit);
                addBytes(opcode);
                binaryInstructions[i++] = opcode;
            } catch(NullPointerException ex){
                CodeError = instruction;
                throw ex;
            }

	    }
		else if (checkAbsoluteX(instruction)){
			modebit = 9;
			addToQueue(instName, modebit, params);
		}
		else if (checkAbsoluteY(instruction)){
			modebit = 10;
			addToQueue(instName, modebit, params);
		}
		else if (checkIndirect(instruction)){
	    	modebit = 11;
	    	addToQueue(instName, modebit, params);
	    }
	    else if (checkImmediate(instruction)){
	    	modebit = 3;
	    	addToQueue(instName, modebit, params);
	    }
		else if (checkAbsolute(instruction)){
			modebit = 8;

			addToQueue(instName, modebit, params);
		}
	    else if (checkIndirectLabel(instruction)){
	    	modebit = 11;
	    	int index = 0;
	    	int tmp = getLabelIndex(params[1].replaceAll("\\p{P}",""));

	    	//minus 1 for previous line, add 1 byte to it.
			if(tmp == 0){
				index = lineLookup[tmp] + 1;
			} else {
				index = lineLookup[tmp-1] + 1;
			}
			addToQueue(instName, modebit, index);
	    }
		else if (checkAbsoluteLabel(instruction)){
			modebit = 8;
			int tmp = getLabelIndex(params[1]);
			int index = lineLookup[tmp] + 1;
			addToQueue(instName, modebit, index);
		}
	    else if (checkIndirectX(instruction)){
	    	modebit = 12;
	    	addToQueue(instName, modebit, params);
	    }
	    else if (checkIndirectY(instruction)){
	    	modebit = 13;
	    	addToQueue(instName, modebit, params);
	    }
	    else if (checkAccumulator(instruction)){
	    	modebit = 2;
	    	int opcode= db.getOPCode(instName, modebit);
			addBytes(opcode);
			binaryInstructions[i++] = opcode;
	    }
	    else if (checkZeroPageX(instruction)){
	    	modebit = 5;
	    	addToQueue(instName, modebit, params);
	    }
	    else if (checkZeroPageY(instruction)){
	    	modebit = 6;
	    	addToQueue(instName, modebit, params);
	    }
	    else if (checkZeroPage(instruction)){
	    	modebit = 4;
	    	addToQueue(instName, modebit, params);
	    }
	    else if (checkBranch(instruction)){
	    	modebit = 7;
	    	int index = 0;   	
			int tmp = getLabelIndex(params[1]);
			//minus 1 for previous line, add 1 byte to it.
			if(tmp == 0){
				index = lineLookup[tmp] + 1;
			} else {
				index = lineLookup[tmp-1] + 1;
			}
			addToQueue(instName, modebit, index);		
	    }else {
	        if(instruction.trim().charAt(instruction.length()-1) != ':' || instruction.isEmpty()){
                CodeError = instruction;
                throw new java.lang.NullPointerException();
            }
		}
		return modebit;
	}

	private void addToQueue(String instName, int modebit, String[] params) {
		int opcode= db.getOPCode(instName, modebit);
		addBytes(opcode);
		int paramNum = Integer.parseInt( params[1].replaceAll("[^0-9A-Fa-f]+", ""), 16);
    	binaryInstructions[i++] = opcode;
    	// check if 16 bit
    	if (modebit >= 8 && modebit <= 11){
    		binaryInstructions[i++] = (paramNum & 0xff);
    		binaryInstructions[i++] = ((paramNum >> 8) & 0xff);
    	}
    	else{
    		binaryInstructions[i++] = paramNum;
    	}
    	
	}
	
	private void addToQueue(String instName, int modebit, int param) {
		// TODO Auto-generated method stub
		int opcode= db.getOPCode(instName, modebit);
		addBytes(opcode);
		binaryInstructions[i++] = opcode;
		binaryInstructions[i++] = param;
	}

	private boolean checkImmediate(String inst){
		String pattern = "#\\$?[0-9a-fA-F]{1,2}";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(inst);
		return m.find();
	}
	private boolean checkImplicit(String inst){
		if (inst.contains(" ")){
			String[] s = inst.split(" ");
			if (s.length > 1) return false;
		}
		return true;
	}
	private boolean checkAccumulator(String inst){
		if (inst.contains(" ")){
			String[] s = inst.split(" ");
			if (s.length < 2) return false;
			String arg = s[1];
			if (!arg.equals("A")) return false;
		}
		return true;
	}
	private boolean checkZeroPage(String inst){
		String pattern = "(\\$| )[0-9a-fA-F]{2}$";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(inst);
		return m.find();
	}
	private boolean checkZeroPageX(String inst){
		String pattern = "\\$?[0-9a-fA-F]{2},X";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(inst);
		return m.find();
	}
	private boolean checkZeroPageY(String inst){
		String pattern = "\\$?[0-9a-fA-F]{2},Y";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(inst);
		return m.find();
	}
	private boolean checkBranch(String inst){
		String pattern = "B\\w*";
		String argPattern = "\\w*";
		Pattern argMatcher = Pattern.compile(argPattern);
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(inst);
		if (m.find()){
			String[] s = inst.split(" ");
			if (s.length < 2) return false;
			String arg = s[1];
			Matcher mArg = argMatcher.matcher(arg);
			return mArg.find();
		}
		return false;
	}
	private boolean checkAbsolute(String inst){
		String pattern = "(\\$| )[0-9a-fA-F]{3,4}$";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(inst);
		return m.find();
	}
	private boolean checkAbsoluteLabel(String inst){
		String pattern = "(JMP|JSR) [0-9a-zA-Z]+";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(inst);
		if (m.find()){
			String[] s = inst.split(" ");
			if (s.length < 2) return false;
			String arg = s[1];
			String p2 = "[0-9a-zA-Z]+";
			Pattern r2 = Pattern.compile(p2);
			Matcher m2 = r2.matcher(arg);
			return m2.find();
		}
		return false;
	}
	private boolean checkAbsoluteX(String inst){
		String pattern = "\\$?[0-9a-fA-F]{3,4},X";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(inst);
		return m.find();
	}
	private boolean checkAbsoluteY(String inst){
		String pattern = "\\$?[0-9a-fA-F]{3,4},Y";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(inst);
		return m.find();
	}
	private boolean checkIndirect(String inst){
		String pattern = "\\(\\$?[0-9a-fA-F]{3,4}\\)";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(inst);
		return m.find();
	}
	private boolean checkIndirectLabel(String inst){
		String pattern = "JMP \\([0-9a-zA-Z]+\\)";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(inst);
		if (m.find()){
			String[] s = inst.split(" ");
			if (s.length < 2) return false;
			String arg = s[1];
			String p2 = "\\([0-9a-zA-Z]+\\)";
			Pattern r2 = Pattern.compile(p2);
			Matcher m2 = r2.matcher(arg);
			return m2.find();
		}
		return false;
	}
	private boolean checkIndirectX(String inst){
		String pattern = "\\(\\$?[0-9a-fA-F]{1,2},X\\)";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(inst);
		return m.find();
	}
	private boolean checkIndirectY(String inst){
		String pattern = "\\(\\$?[0-9a-fA-F]{1,2}\\),Y";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(inst);
		return m.find();
	}




}
