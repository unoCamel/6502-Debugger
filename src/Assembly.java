import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Assembly{

	private String[] instructions;
	private HashMap<String, Integer> labels;

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
		int[] binaryInstructions = new int[instructions.length];

		for (String instruction  : instructions) {
			if (instruction.equals("LABEL")) continue;
			checkMode(instruction);
			switch (instruction.substring(0, 3)) {
				case "ADC":	break;
				case "AND":	break;
				case "ASL":	break;
				case "BCC":	break;
				case "BCS":	break;
				case "BEQ":	break;
				case "BIT":	break;
				case "BMI":	break;
				case "BNE":	break;
				case "BPL":	break;
				case "BRK":	break;
				case "BVC":	break;
				case "BVS":	break;
				case "CLC":	break;
				case "CLD":	break;
				case "CLI":	break;
				case "CLV":	break;
				case "CMP":	break;
				case "CPX":	break;
				case "CPY":	break;
				case "DEC":	break;
				case "DEX":	break;
				case "DEY":	break;
				case "EOR":	break;
				case "INC":	break;
				case "INX":	break;
				case "INY":	break;
				case "JMP":	break;
				case "JSR":	break;
				case "LDA":	break;
				case "LDX":	break;
				case "LDY":	break;
				case "LSR":	break;
				case "NOP":	break;
				case "ORA":	break;
				case "PHA":	break;
				case "PHP":	break;
				case "PLA":	break;
				case "PLP":	break;
				case "ROL":	break;
				case "ROR":	break;
				case "RTI":	break;
				case "RTS":	break;
				case "SBC":	break;
				case "SEC":	break;
				case "SED":	break;
				case "SEI":	break;
				case "STA":	break;
				case "STX":	break;
				case "STY":	break;
				case "TAX":	break;
				case "TAY":	break;
				case "TSX":	break;
				case "TXA":	break;
				case "TXS":	break;
				case "TYA":	break;
				default:	break;


			}

		}
}

	private int checkMode(String instruction){
			int modebit = 0;
			// (1) Implicit / (2) Accumulator / (3) Immediate / (4) Zero Page / (5) Zero Page,X / (6) Zero Page,Y / (7) Relative
			// (8) Absolute / (9) Absolute,X / (10) Absolute,Y / (11) Indirect / (12) (Indirect,X) / (13) (Indirect,Y)
	    if (checkImplicit(instruction)){modebit = modebit | (1 << 0);}
	    if (checkAccumulator(instruction)){modebit = modebit | (1 << 1);}
	    if (checkImmediate(instruction)){modebit = modebit | (1 << 2);}
	    if (checkZeroPage(instruction)){modebit = modebit | (1 << 3);}
	    if (checkZeroPageX(instruction)){modebit = modebit | (1 << 4);}
	    if (checkZeroPageY(instruction)){modebit = modebit | (1 << 5);}
	    if (checkRelative(instruction)){modebit = modebit | (1 << 6);}
	    if (checkAbsolute(instruction)){modebit = modebit | (1 << 7);}
	    if (checkAbsoluteX(instruction)){modebit = modebit | (1 << 8);}
	    if (checkAbsoluteY(instruction)){modebit = modebit | (1 << 9);}
	    if (checkIndirect(instruction)){modebit = modebit | (1 << 10);}
	    if (checkIndirectX(instruction)){modebit = modebit | (1 << 11);}
	    if (checkIndirectY(instruction)){modebit = modebit | (1 << 12);}
			return modebit;
	}

	private boolean checkImmediate(String inst){
		String pattern = "#\\$[0-9a-f]{1,2}";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(inst);
		return m.find();
	}
	private boolean checkImplicit(String inst){
		return true;
	}
	private boolean checkAccumulator(String inst){
		return true;
	}
	private boolean checkZeroPage(String inst){
		return true;
	}
	private boolean checkZeroPageX(String inst){
		return true;
	}
	private boolean checkZeroPageY(String inst){
		return true;
	}
	private boolean checkRelative(String inst){
		return true;
	}
	private boolean checkAbsolute(String inst){
		return true;
	}
	private boolean checkAbsoluteX(String inst){
		return true;
	}
	private boolean checkAbsoluteY(String inst){
		return true;
	}
	private boolean checkIndirect(String inst){
		return true;
	}
	private boolean checkIndirectX(String inst){
		return true;
	}
	private boolean checkIndirectY(String inst){
		return true;
	}




}
