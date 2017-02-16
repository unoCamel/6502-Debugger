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
				return null; //returns null if String is empty or null
			}

			nlIndex = str.indexOf('\n', currentIndex);
			if (nlIndex == -1){
				if (str.charAt(str.length() - 1) != '\n'){
					//handles case where string does not end in newline
					String ins = str.substring(currentIndex, str.length());
					//creates last substring not ended in a newline
					ins = ins.trim();
					//removes any whitespace on edges of string
					strArray[memCounter] = ins;
					break;
				}
				break;
			}

			String ins = str.substring(currentIndex, nlIndex + 1);
			//creates substring that ends in newline
			currentIndex += ins.length();
			ins = (ins.replace('\n', ' ')).trim();
			//removes newline char and whitespace on edges
			strArray[memCounter] = ins;
			memCounter += 1;
		}
		return strArray;
	}
}