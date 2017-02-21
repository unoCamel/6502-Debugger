import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Assembly{

	private String[] instructions;
	private HashMap<String, Integer> labels;
	private int[] binaryInstructions;
	private int i = 0;


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

	/*@brief Initializes Assembly class, purpose is to hold instruction and
	* their corresponding labels.
*
* @param String[] ins all instructions, HashMap<String, Integer> ls labels
* as keys and indexes of labels as values.
* @return None.
*/
public void assemble(){
		binaryInstructions = new int[Global.MAX_MEMORY/8];
		i = 0;
		for (String instruction  : instructions) {
			if (instruction.equals("LABEL")) {
				binaryInstructions[i++] = 0xEA ; //NOP
				continue;
			}
			setupQueue(instruction);			
			}
		Memory.setMemory(binaryInstructions);
		}
		


	private int setupQueue(String instruction){
		int modebit = 0;
			// (1) Implicit / (2) Accumulator / (3) Immediate / (4) Zero Page / (5) Zero Page,X / (6) Zero Page,Y / (7) Relative
			// (8) Absolute / (9) Absolute,X / (10) Absolute,Y / (11) Indirect / (12) (Indirect,X) / (13) (Indirect,Y)
		// have to run Immediate check before before ZeroPage
		String instName = instruction.substring(0, 3);
		String[] params = instruction.split(" ");
	    if (checkImplicit(instruction)){
	    	modebit = modebit | (1 << 0);
	    	int opcode= Databank.getOPCode(instName, modebit);
	    	binaryInstructions[i++] = opcode;
	    }
	    else if (checkAccumulator(instruction)){
	    	modebit = modebit | (1 << 1);
	    }
	    else if (checkImmediate(instruction)){
	    	modebit = modebit | (1 << 2);
	    	addToQueue(instName, modebit, params);
	    }
	    else if (checkZeroPage(instruction)){
	    	modebit = modebit | (1 << 3);
	    	addToQueue(instName, modebit, params);
	    }
	    else if (checkZeroPageX(instruction)){
	    	modebit = modebit | (1 << 4);
	    	addToQueue(instName, modebit, params);
	    }
	    else if (checkZeroPageY(instruction)){
	    	modebit = modebit | (1 << 5);
	    	addToQueue(instName, modebit, params);
	    }
	    else if (checkBranch(instruction)){
	    	modebit = modebit | (1 << 6);
			int index = getLabelIndex(params[1]);
			addToQueue(instName, modebit, index);		
	    }
	    else if (checkAbsolute(instruction)){
	    	modebit = modebit | (1 << 7);
	    	addToQueue(instName, modebit, params);
	    }
	    else if (checkAbsoluteX(instruction)){
	    	modebit = modebit | (1 << 8);
	    	addToQueue(instName, modebit, params);
	    }
	    else if (checkAbsoluteY(instruction)){
	    	modebit = modebit | (1 << 9);
	    	addToQueue(instName, modebit, params);
	    }
	    else if (checkIndirect(instruction)){
	    	modebit = modebit | (1 << 10);
	    	addToQueue(instName, modebit, params);
	    }
	    else if (checkIndirectX(instruction)){
	    	modebit = modebit | (1 << 11);
	    	addToQueue(instName, modebit, params);
	    }
	    else if (checkIndirectY(instruction)){
	    	modebit = modebit | (1 << 12);
	    	addToQueue(instName, modebit, params);
	    }
		return modebit;
	}

	private void addToQueue(String instName, int modebit, String[] params) {
		// TODO Auto-generated method stub
		int opcode= Databank.getOPCode(instName, modebit);
    	int paramNum = Integer.parseInt( params[1].replaceAll("[^-?0-9]+", ""), 16);
    	binaryInstructions[i++] = opcode;
    	binaryInstructions[i++] = paramNum;  
	}
	
	private void addToQueue(String instName, int modebit, int param) {
		// TODO Auto-generated method stub
		int opcode= Databank.getOPCode(instName, modebit);
    	binaryInstructions[i++] = opcode;
    	binaryInstructions[i++] = param;  
	}

	private boolean checkImmediate(String inst){
		String pattern = "#\\$[0-9a-f]{1,2}";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(inst);
		return m.find();
	}
	private boolean checkImplicit(String inst){
		String[] s = inst.split(" ");
		if (s.length < 1) return false;
		return true;
	}
	private boolean checkAccumulator(String inst){
		String[] s = inst.split(" ");
		if (s.length < 2) return false;
		String arg = s[1];
		if (!arg.equals("A")) return false;
		return true;
	}
	private boolean checkZeroPage(String inst){
		String pattern = "\\$[0-9a-f]{1,2}$";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(inst);
		return m.find();
	}
	private boolean checkZeroPageX(String inst){
		String pattern = "\\$[0-9a-f]{1,2},X";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(inst);
		return m.find();
	}
	private boolean checkZeroPageY(String inst){
		String pattern = "\\$[0-9a-f]{1,2},Y";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(inst);
		return m.find();
	}
	private boolean checkBranch(String inst){
		String pattern = "B\\w*";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(inst);
		if (m.find()){
			String[] s = inst.split(" ");
			if (s.length < 2) return false;
			String arg = s[1];
			Matcher m1 = r.matcher(arg);
			return m1.find();
		}
		return false;
	}
	private boolean checkAbsolute(String inst){
		String pattern = "\\$[0-9a-f]{3,4}";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(inst);
		return m.find();
	}
	private boolean checkAbsoluteX(String inst){
		String pattern = "\\$[0-9a-f]{3,4},X";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(inst);
		return m.find();
	}
	private boolean checkAbsoluteY(String inst){
		String pattern = "\\$[0-9a-f]{3,4},Y";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(inst);
		return m.find();
	}
	private boolean checkIndirect(String inst){
		String pattern = "\\(\\$[0-9a-f]{3,4}\\)";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(inst);
		return m.find();
	}
	private boolean checkIndirectX(String inst){
		String pattern = "\\(\\$[0-9a-f]{1,2},X\\)";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(inst);
		return m.find();
	}
	private boolean checkIndirectY(String inst){
		String pattern = "\\(\\$[0-9a-f]{1,2}\\),Y";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(inst);
		return m.find();
	}




}
