import java.util.Arrays;
import java.util.HashMap;

public class Import {

	/*
		@brief: Splits a string into an array of instructions.
		"LD A B \n ADC A B \n LD A"
		@param: str : the string of instructions to be parsed
		@return: Assembly
	*/
	public static Assembly importInstructions(String str){
		int index = 0;
		int nlindex = 0;
		int scindex = 0;
		int cindex = 0;
		int memCounter = 0;

		String[] instructions = new String[Global.MAX_MEMORY/8];
		HashMap<String, Integer> labels = new HashMap<String, Integer>();

		while (memCounter < Global.MAX_MEMORY/8){
			nlindex = str.indexOf('\n', index);
			if (nlindex != -1){
				//there is a new line
				String ins = str.substring(index, nlindex + 1);
				ins = ins.replace('\n', ' ').trim();
				if (ins.length() == 0){
					//if empty string
					index = nlindex + 1;
					continue;
				}
				scindex = ins.indexOf(';');
				cindex = ins.indexOf(':');
				if (scindex != -1){
					//there is a comment
					ins = ins.substring(0, scindex);
				}
				if (cindex != -1){
					//there is a lable
					if (cindex == ins.length() - 1){
						//there is only the label
						ins = ins.replace(':', ' ').trim();
						instructions[memCounter] = "LABEL";
						labels.put(ins, memCounter);
						memCounter += 1;
						index = nlindex + 1;
						continue;
					}
					else{
						//there is label and instruction
						String label = ins.substring(0, cindex + 1);
						label = label.replace(':', ' ').trim();
						ins = ins.substring(cindex + 1, ins.length());
						ins = ins.trim();
						labels.put(label, memCounter);
					}
				}
				ins = ins.trim();
				instructions[memCounter] = ins;
				memCounter += 1;
				index = nlindex + 1;
			}
			else{
				//there is no newline -- last instruction
				String ins = str.substring(index, str.length());
				ins = ins.trim();
				if (ins.length() != 0){
					scindex = ins.indexOf(';');
					cindex = ins.indexOf(':');
					if (scindex != -1){
						//there is a comment
						ins = ins.substring(0, scindex);
					}
					if (cindex != -1){
						//there is a lable
						if (cindex == ins.length() - 1){
							//there is only the label
							ins = ins.replace(':', ' ').trim();
							instructions[memCounter] = "LABEL";
							labels.put(ins, memCounter);
							break;
						}
						else{
							//there is label and instruction
							String label = ins.substring(0, cindex + 1);
							label = label.replace(':', ' ').trim();
							ins = ins.substring(cindex + 1, ins.length());
							ins = ins.trim();
							instructions[memCounter] = ins;
							labels.put(label, memCounter);
							break;
						}
					}
					instructions[memCounter] = ins;
					break;
				}
				break;
			}
		}
		Assembly assembly = new Assembly(instructions, labels);
		return assembly;
	}
}