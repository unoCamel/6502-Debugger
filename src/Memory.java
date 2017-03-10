import java.util.Arrays;

public class Memory {

    private static Integer[] ZeroPage = new Integer[0x100];
    private static Integer[] Stack = new Integer[0x100];
    public static Integer[] RAM = new Integer[0x3F01]; //0x0000-0x3FFFF This is for instructions, and the header RAM file. 32kb of information.
    private static Integer[] ROM = new Integer[0xFFFF]; //0x
    //TODO We aren't going to implement these yet. More hardware based.
//	private static int[] VIA1;
//	private static int[] VIA2;
//	private static int[] VIA3;
//	private static int[] ACIA1;
//	private static int[] ACIA2;
//	private static int[] ACIA3;

    /*@brief Initializes a memory of size 65kb
	* 
	* @param None.
	* @return boolean True if successfully created
	*/
    //TODO Add memory check
    public Memory() {
        ZeroPage = new Integer[0xFF]; //first 256 words
        Stack = new Integer[0xFF]; //Stack space 0x0100 to 0x01FF
        RAM = new Integer[0x3F00]; //Only half of 32kb, ZeroPage conceptually is inside here. We will ignore empty space for now.
        ROM = new Integer[0x8000]; //
    //		VIA1 = new Integer[0x3FFF];
    //		VIA2 = new Integer[0x3FFF];
    //		VIA3 = new Integer[0x3FFF];
    //		ACIA1 = new Integer[0x3FFF];
    //		ACIA2 = new Integer[0x3FFF];
    //		ACIA3 = new Integer[0x3FFF];
    }

    /*@brief Initializes a memory of size 65kb, and fills the memory with the inputted
	*         list of binary instructions.
	* @param binaryInstructions An Integer[] of 8-bit binary values.
	* @return None
	*/
    public Memory(Integer[] binaryInstructions) {
        RAM = Arrays.copyOf(binaryInstructions, 0x3FFF); //Instructions will only be written to RAM. 0x3F00 is RAM size minus ZeroPage.
    }

    /*@brief Memory is loaded with the inputted binary instructions, starting
	*		  at 0x0100.
	* @param binaryInstructions An Integer[] of 8-bit binary values.
	* @return void.
	*/
    public static void setMemory(Integer[] binaryInstructions) {
        try {
            RAM = Arrays.copyOf(binaryInstructions, 0x3FFF); //Instructions will only be written to RAM. 0x3F00 is RAM size minus ZeroPage.
        } catch (NullPointerException e) {
            System.err.println("NullPointerException, no instructions provided. Message: " + e);
        }
    }


    /*@brief Initializes a memory of size 65kb
	* @important THIS MACHINE IS LITTLE-ENDIAN
	* @param None.
	* @return void.
	*/
    public static void write(int index, int value) {
        if (index >= 0x0000 && index <= 0x00FF) {ZeroPage[index] = value;}
        else if (index >= 0x0100 && index <= 0x01FF) {Stack[(index-0x0100)] = value;}
        else if (index >= 0x0200 && index <= 0x3FFF) {RAM[(index-0x0200)] = value;}
        else if (index >= 0x8000 && index <= 0xFFFF) {ROM[(index-0x8000)] = value;}
        else {
            System.err.println("NullMemoryException, trying to write to invalid memory.");
        }
    }

    /*@brief Finds the binary instruction at the supplied index
	*
	* @param index 16-bit number of index in memory
	* @return 8-bit binary instruction
	*/
    public static Integer read(int index) {
        if (index >= 0x0000 && index <= 0x00FF) {return (ZeroPage[index] == null) ? 0 : ZeroPage[(index)];}
        else if (index >= 0x0100 && index <= 0x01FF) {return (Stack[(index-0x0100)]== null) ? 0 : Stack[(index-0x0100)];}
        else if (index >= 0x0200 && index <= 0x3FFF) {return (RAM[(index-0x0200)] == null) ? 0 : RAM[(index-0x0200)];}
        else if (index >= 0x4000 && index <= 0xFFFF) {return (ROM[(index-0x4000)] == null) ? 0 : ROM[(index-0x4000)];}
        else {
            System.err.println("NullMemoryException, trying to access invalid memory.");
            return -1;
        }
    }
    //TODO does not work yet.
    /*@brief Finds all binary instructions between two indexes. Two indexes are needed to be in the same
	* section of memory, otherwise an error will be returned.
	* @param index1 The first index of the range
	* @param index2 The second index of the range
	* @return An array of indexes between index1 and index2, inclusive, or null if out of range.
	*/
    public static Integer[] readRange(int index1, int index2) {
        Integer[] tempMemory;
        if (index1 >= 0x0000 && index2 <= 0x00FF) {tempMemory = Arrays.copyOfRange(ZeroPage, index1, index2);}
        //else if (index1 >= 0x0200 && index2 <= 0x3FFF) {tempMemory = Arrays.copyOfRange(RAM, index1-0x0200, index2-0x0200);}
        else if (index1 >= 0x8000 && index2 <= 0xFFFF) {tempMemory = Arrays.copyOfRange(ROM, index1-0x8000, index2-0x8000);}
        else {
            System.err.println("InvalidMemoryAccessException, requested memory must be in the same section.");
            return null;
        }
        return tempMemory;
    }

    /*@brief Prints the array of binary instructions
	*
	* @param None.
	* @return String.
	*/
    public static void instrToString() {
        int counter = 0;
        for (Integer n: RAM){
            if(n == null){
                break;
            }
            if (n == 0){
                counter++;}
            if(counter > 5) {
                break;
            }
            System.out.print(Integer.toHexString(n) + ", ");
        }
    }

    /*@brief Prints the array of binary instructions
*
* @param None.
* @return String.
*/
    public static String memoryToString() {
        int counter = 0;
        int tmpInstr;
        int tmpArg1;
        int tmpArg2;
        String memoryString = "";
        String binaryString = "";
        counter = 0;

        for(int x = 0; x < 0x3FFF; x += 0x10){
            binaryString = "";
            memoryString = memoryString.concat("$" + String.format("%04x", (int) x).toUpperCase() + ": ");
            for(int n = 0; n < 0x10; n++){
                if(n == 0x08){memoryString = memoryString.concat(" |");}
                tmpInstr = read(counter++);
                if(tmpInstr == 0x00){
                    binaryString = binaryString.concat(".");
                } else {
                    binaryString = binaryString.concat(Character.toString((char)tmpInstr));
                }
                memoryString = memoryString.concat(" " + String.format("%02x", (int) tmpInstr).toUpperCase());
                if(n == 0x0F){memoryString = memoryString.concat(" | " + binaryString);}
            }
            memoryString = memoryString.concat("\n");
        }
        return memoryString;
    }

    public static String stackToString() {
        int counter = 0;
        int tmpInstr;
        int tmpInstr2;
        int tmpArg1;
        int tmpArg2;
        int byteSize;
        String memoryString = "";

        for(int x = 0; x <= 0x0200; x += 0x02){
            tmpInstr = read(counter++);
            tmpInstr2 = read(counter++);
            if(x <= 0xFF){
                memoryString = memoryString.concat("ZP:" + String.format("%02x", (int) x).toUpperCase());
                memoryString = memoryString.concat(String.format(" "+"%02x", (int) tmpInstr).toUpperCase() + String.format("%02x", (int) tmpInstr2).toUpperCase() + "\n");
            }
            else if(x <= 0x01FF){
                memoryString = memoryString.concat("HRAM:" + String.format("%02x", (int) x).toUpperCase());
                memoryString = memoryString.concat(String.format(" "+"%02x", (int) tmpInstr).toUpperCase() + String.format("%02x", (int) tmpInstr2).toUpperCase() + "\n");
            }

        }
        counter = 0x200;
        int instrCounter = 0;
        for(int x = 0x0200; x <= 0x0400; x += 0x01){
            tmpInstr = read(counter);
            tmpArg1 = read(counter + 1);
            tmpArg2 = read(counter + 2);
            byteSize = Databank.getJumpCode(tmpInstr);

            memoryString = memoryString.concat("$" + String.format("%02x", (int) x).toUpperCase() + ": ");

            switch(byteSize){
                case 1:
                    if(DebuggerGUI.currentInstructions[instrCounter] != null){
                        if(DebuggerGUI.currentInstructions[instrCounter].equals("LABEL")){
                            instrCounter++;
                        }
                        memoryString = memoryString.concat(String.format("%02x", (int) tmpInstr).toUpperCase()+ "       " + DebuggerGUI.currentInstructions[instrCounter++] + "\n");
                    } else {
                        memoryString = memoryString.concat(String.format("%02x", (int) tmpInstr).toUpperCase()+ "       " + "BRK" + "\n");
                        instrCounter++;
                    }
                    break;
                case 2:
                    memoryString = memoryString.concat(String.format("%02x", (int) tmpInstr).toUpperCase() + " " + String.format("%02x", (int) tmpArg1).toUpperCase() + "    " + DebuggerGUI.currentInstructions[instrCounter++] +  "\n");
                    break;
                case 3:
                    memoryString = memoryString.concat(String.format("%02x", (int) tmpInstr).toUpperCase() + " " + String.format("%02x", (int) tmpArg2).toUpperCase() + " " + String.format("%02x", (int) tmpArg1).toUpperCase() + " " + DebuggerGUI.currentInstructions[instrCounter++] +"\n");
                    break;
            }
            x += byteSize-1;
            counter += byteSize;

            //memoryString = memoryString.concat(String.format(" "+"%02x", (int) tmpInstr) + " " + String.format("%02x", (int) tmpArg1) + "\n");

        }
        return memoryString;

    }

//    public static void memoryToString() {
//        int counter = 0;
//        int zpIndex, stackIndex, ramIndex, romIndex;
//        int tmpInstr;
//        int tmpArg1;
//        int tmpArg2;
//        String memoryString = "";
//
//        for(int x = 0; x < 0x200; x +=10){
//            for(int n = 0; n < 10; n++){
//                tmpInstr = read(x);
//                memoryString.concat("$" + Integer.toHexString(x) + ": " + tmpInstr + "\n");
//            }
//
//
//        }
//        for(int x = 200; x < 0xFFFF; x++){
//            tmpInstr = read(x);
//
//        }
//
//
//    }

    /*@brief Prints provided 8-bit number at specified index.
    *
    * @param index Intended value to retrieve from memory..
    * @return String.
    */
    public static void instrToString(int index) {
        System.out.println(read(index));
    }

    /*@brief Prints the array of binary instructions between index1 and index2.
	*
	* @param index1 The first index of the range
	* @param index2 The second index of the range
	* @return String.
	*/
    public static void instrToString(int index1, int index2) { System.out.println(Arrays.toString(readRange(index1, index2))); }

    /*@brief replace each value with zero in Memory.
	*
	* @param None.
	* @return Void.
	*/
    public static void clean() {
        Arrays.fill(ZeroPage, 0);
        Arrays.fill(ROM, 0);
        Arrays.fill(RAM, 0);
    }

    /*@brief enumerated type to find out what range of memory something should be placed in.
    *
    * @param None.
    * @return boolean True if in certain range.
    */
//    public static enum memoryRange {
//        inROM, inRAM, inZeroPage;
//        public boolean inRange(int val) {
//            switch (this) {
//                case inZeroPage:
//                    return (val >= 0x0000 && val <= 0x00FF);
//                case inRAM:
//                    return (val >= 0x0100 && val <= 0x3FFF);
//                case inROM:
//                    return (val >= 0x8000 && val <= 0xFFFF);
//            }
//            System.err.println("NullMemoryException, trying to access invalid memory.");
//            return false;
//        }
//
//        public static memoryRange getValue(int val) {
//            if (val >= 0x0000 && val <= 0x00FF) {return inZeroPage;}
//            if (val >= 0x0100 && val <= 0x3FFF) {return inRAM;}
//            if (val >= 0x8000 && val <= 0xFFFF) {return inROM;}
//            else {
//                System.err.println("NullMemoryException, trying to access invalid memory.");
//                return null;
//            }
//        }
//  }
}