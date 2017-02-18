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
			scindex = str.indexOf(';', index);
			String ins = "";

			if (scindex == nlindex){
				//there is no comments and no newlines -- last instruction
				ins = str.substring(index, str.length());
				ins = ins.trim();
				if (!ins.equals("")){
					cindex = ins.indexOf(":");
					if (cindex != -1){
						//there is a label
						if (cindex == ins.length() - 1){
							//only label, store in hashmap, mark LABEL in array
							ins = ins.replace(':', ' ').trim();
							labels.put(ins, memCounter);
							instructions[memCounter] = "LABEL";
						}
						else{
							//label and instruction, store label in hashmap, put 
							//only instruction in array
							String l = ins.substring(0, cindex + 1);
							l = l.replace(':', ' ').trim();
							ins = ins.substring(cindex + 1, ins.length());
							ins = ins.trim();
							labels.put(l, memCounter);
							instructions[memCounter] = ins;
						}
					}
					else{
						//no label, place instruction in array
						instructions[memCounter] = ins;
					}
				}
				Assembly assembly = new Assembly(instructions, labels);
				return assembly;
			}
			else if(scindex == -1){
				//there is no comment
				ins = str.substring(index, nlindex + 1);
				ins = ins.replace('\n', ' ').trim();
				index = nlindex + 1;
			}
			else if(nlindex == -1){
				//last line with comment
				ins = str.substring(index, scindex);
				ins = ins.replace(';', ' ').trim();
				if (!ins.equals("")){
					cindex = ins.indexOf(":");
					if (cindex != -1){
						//there is a label
						if (cindex == ins.length() - 1){
							//only label, store in hashmap, mark LABEL in array
							ins = ins.replace(':', ' ').trim();
							labels.put(ins, memCounter);
							instructions[memCounter] = "LABEL";
						}
						else{
							//label and instruction, store label in hashmap, put 
							//only instruction in array
							String l = ins.substring(0, cindex + 1);
							l = l.replace(':', ' ').trim();
							ins = ins.substring(cindex + 1, ins.length());
							ins = ins.trim();
							labels.put(l, memCounter);
							instructions[memCounter] = ins;
						}
					}
					else{
						//no label, place instruction in array
						instructions[memCounter] = ins;
					}
				}
				Assembly assembly = new Assembly(instructions, labels);
				return assembly;
			}
			else if(nlindex < scindex && nlindex != -1){
				//if comment with instruction, new line comes before comment
				ins = str.substring(index, nlindex + 1);
				ins = ins.replace('\n', ' ').trim();
				index = nlindex + 1;			
			}
			else if(nlindex > scindex && scindex != -1){
				//if comment with instruction , new line after comment;
				ins = str.substring(index, scindex + 1);
				ins = ins.replace(';', ' ').trim();
				index = nlindex + 1;
			}

			if (!ins.equals("")){
				cindex = ins.indexOf(":");
				if (cindex != -1){
					//there is a label
					if (cindex == ins.length() - 1){
						//only label, store in hashmap, mark LABEL in array
						ins = ins.replace(':', ' ').trim();
						labels.put(ins, memCounter);
						instructions[memCounter] = "LABEL";
					}
					else{
						//label and instruction, store label in hashmap, put 
						//only instruction in array
						String l = ins.substring(0, cindex + 1);
						l = l.replace(':', ' ').trim();
						ins = ins.substring(cindex + 1, ins.length());
						ins = ins.trim();
						labels.put(l, memCounter);
						instructions[memCounter] = ins;
					}
				}
				else{
					//no label, place instruction in array
					instructions[memCounter] = ins;
				}
				memCounter += 1;
			}

		}
		Assembly assembly = new Assembly(instructions, labels);
		return assembly;
	}
}

