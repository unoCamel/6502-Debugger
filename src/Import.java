package src;

public class Import {

	/*
		@brief: Splits a string into an array of instructions.
		"LD A B \n ADC A B \n LD A"
		@param: str : the string of instructions to be parsed
		@return: String[]
	*/
	public static String[] importInstructions(String str){
		int nlIndex = 0;
		int memCounter = 0;
		int currentIndex = 0;
		String[] strArray = new String[Global.MAX_MEMORY];

		while (memCounter < Global.MAX_MEMORY){
			if (str.equals("") || str == null){
				return null;
			}

			nlIndex = str.indexOf('\n', currentIndex);
			if (nlIndex == -1){
				if (str.charAt(str.length() - 1) != '\n'){
					String ins = str.substring(currentIndex, str.length());
					ins = (ins.replace('\n', ' ')).trim();
					strArray[memCounter] = ins;
					break;
				}
				break;
			}

			String ins = str.substring(currentIndex, nlIndex + 1);
			currentIndex += ins.length();
			ins = (ins.replace('\n', ' ')).trim();
			strArray[memCounter] = ins;
			memCounter += 1;
		}
		return strArray;
	}
}