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

		String[] instructions = new String[Global.MAX_MEMORY];
		HashMap<String, Integer> labels = new HashMap<String, Integer>();

		while (memCounter < Global.MAX_MEMORY){
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
					ins = ins.trim();
				}
				if (cindex != -1){
					//there is a label
					if (cindex == ins.length() - 1 ){
						//there is only the label
						if (cindex > scindex && scindex != -1){
							index = nlindex + 1;
							continue;
						}
						ins = ins.replace(':', ' ').trim();
						instructions[memCounter] = "LABEL";
						labels.put(ins, memCounter);
						memCounter += 1;
						index = nlindex + 1;
						continue;
					}
					else{
						//there is label and instruction
						if (cindex > scindex && scindex != -1){
							index = nlindex + 1;
							continue;
						}
						String label = ins.substring(0, cindex + 1);
						label = label.replace(':', ' ').trim();
						ins = ins.substring(cindex + 1, ins.length());
						ins = ins.trim();
						labels.put(label, memCounter);
					}
				}
				ins = ins.trim();
				if (ins.length() != 0){
					instructions[memCounter] = ins;
					memCounter += 1;
				}
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
						ins = ins.trim();
					}
					if (cindex != -1){
						//there is a lable
						if (cindex == ins.length() - 1){
							//there is only the label
							if (cindex > scindex && scindex != -1){
								index = nlindex + 1;
								break;
							}
							ins = ins.replace(':', ' ').trim();
							instructions[memCounter] = "LABEL";
							labels.put(ins, memCounter);
							break;
						}
						else{
							//there is label and instruction
							if (cindex > scindex && scindex != -1){
								index = nlindex + 1;
								break;
							}
							String label = ins.substring(0, cindex + 1);
							label = label.replace(':', ' ').trim();
							ins = ins.substring(cindex + 1, ins.length());
							ins = ins.trim();
							instructions[memCounter] = ins;
							labels.put(label, memCounter);
							break;
						}
					}
					if (ins.length() != 0){
						instructions[memCounter] = ins;
					}
					break;
				}
				break;
			}
		}
		Assembly assembly = new Assembly(instructions, labels);
		return assembly;
	}

	public static void testImport(){
		String str0 = "";
		//no input
		String str1 = "WEEKDAY:\nCPX #3          ; Year starts in March to bypass\nBCS MARCH       ; leap year problem\nDEY             ; If Jan or Feb, decrement year\nMARCH:    EOR #$7F        ; Invert A so carry works right\nCPY #200        ; Carry will be 1 if 22nd century\nADC MTAB-1,X    ; A is now day+month offset\nSTA TMP\nTYA             ; Get the year\nJSR MOD7        ; Do a modulo to prevent overflow\nSBC TMP         ; Combine with day+month\nSTA TMP\nTYA             ; Get the year again\nLSR             ; Divide it by 4\nLSR\nCLC             ; Add it to y+m+d and fall through\nADC TMP\nMOD7:     ADC #7          ; Returns (A+3) modulo 7\nBCC MOD7        ; for A in 0..255\nRTS\nMTAB:     DB 1,5,6,3,1,5,3,0,4,2,6,4   	; Month offsets";
		//perfect input
		String str2 = "\n\n\n\nWEEKDAY:\nCPX #3          ; Year starts in March to bypass\nBCS MARCH       ; leap year problem\n\n\n\nDEY             ; If Jan or Feb, decrement year\nMARCH:    EOR #$7F        ; Invert A so carry works right\nCPY #200        ; Carry will be 1 if 22nd century\n\n\n\nADC MTAB-1,X    ; A is now day+month offset\nSTA TMP\nTYA             ; Get the year\nJSR MOD7        ; Do a modulo to prevent overflow\nSBC TMP         ; Combine with day+month\nSTA TMP\nTYA             ; Get the year again\nLSR             ; Divide it by 4\nLSR\nCLC             ; Add it to y+m+d and fall through\nADC TMP\nMOD7:     ADC #7          ; Returns (A+3) modulo 7\nBCC MOD7        ; for A in 0..255\nRTS\nMTAB:     DB 1,5,6,3,1,5,3,0,4,2,6,4   	; Month offsets\n\n\n";
		//extra / unecessary new lines everywhere

		String[] myArray = {str0, str1, str2};

		for (int i = 0; i < myArray.length; i++){
			System.out.println("\n\n\nSTRING TO BE PARSED:\n" + myArray[i]);
			Assembly assembly = importInstructions(myArray[i]);
			System.out.println("INSTRUCTIONS:\n" + Arrays.toString(assembly.getAllInstructions()));
			System.out.println("\nLABELS AND INDEXES:\n");
			(assembly.getAllLabels()).forEach((k,v)-> System.out.println(k+", "+v));
		}

		System.out.println("\n\n\n\nDONE WITH TEST.");
	}

}