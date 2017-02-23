import java.util.Arrays;

public class Memory {

    private static int[] ZeroPage = new int[0xFF];
    private static int[] Stack = new int[0xFF];
    public static int[] RAM = new int[0x3F00]; //0x0000-0x3FFFF This is for instructions, and the header RAM file. 32kb of information.
    private static int[] ROM = new int[0x8000]; //0x
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
        ZeroPage = new int[0xFF]; //first 256 words
        Stack = new int[0xFF]; //Stack space 0x0100 to 0x01FF
        RAM = new int[0x3F00]; //Only half of 32kb, ZeroPage conceptually is inside here. We will ignore empty space for now.
        ROM = new int[0x8000]; //
    //		VIA1 = new int[0x3FFF];
    //		VIA2 = new int[0x3FFF];
    //		VIA3 = new int[0x3FFF];
    //		ACIA1 = new int[0x3FFF];
    //		ACIA2 = new int[0x3FFF];
    //		ACIA3 = new int[0x3FFF];
    }

    /*@brief Initializes a memory of size 65kb, and fills the memory with the inputted
	*         list of binary instructions.
	* @param binaryInstructions An int[] of 8-bit binary values.
	* @return boolean True if successfully created and memory is loaded
	*/
    public Memory(int[] binaryInstructions) {
        RAM = Arrays.copyOf(binaryInstructions, 0x3FFF); //Instructions will only be written to RAM. 0x3F00 is RAM size minus ZeroPage.
    }

    /*@brief Memory is loaded with the inputted binary instructions, starting
	*		  at 0x0100.
	* @param binaryInstructions An int[] of 8-bit binary values.
	* @return void.
	*/
    public static void setMemory(int[] binaryInstructions) {
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
    public static int read(int index) {
        if (index >= 0x0000 && index <= 0x00FF) {return ZeroPage[index];}
        else if (index >= 0x0100 && index <= 0x01FF) {return Stack[(index-0x0100)];}
        else if (index >= 0x0200 && index <= 0x3FFF) {return RAM[(index-0x0200)];}
        else if (index >= 0x8000 && index <= 0xFFFF) {return ROM[(index-0x8000)];}
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
    public static int[] readRange(int index1, int index2) {
        int[] tempMemory;
        if (index1 >= 0x0000 && index2 <= 0x00FF) {tempMemory = Arrays.copyOfRange(ZeroPage, index1, index2);}
        else if (index1 >= 0x0200 && index2 <= 0x3FFF) {tempMemory = Arrays.copyOfRange(RAM, index1-0x0200, index2-0x0200);}
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
        System.out.println(Arrays.toString(RAM));
    }

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