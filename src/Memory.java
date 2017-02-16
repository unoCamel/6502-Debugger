//TODO Discuss zero page implementation


import java.util.Arrays;

public class Memory {

	private static int[] memory;


	/*@brief Initializes a memory of size 65kb
	* 
	* @param None.
	* @return boolean True if successfully created
	*/
	public boolean Memory(){
		memory = new int[Global.MAX_MEMORY];
		return true;
	}

	/*@brief Initializes a memory of size 65kb, and fills the memory with the inputted
	*         list of binary instructions.
	* @param binaryInstructions An int[] of 8-bit binary values.
	* @return boolean True if successfuly created and memory is loaded
	*/
	public boolean Memory(int[] binaryInstructions){
		memory = Arrays.copyOf(binaryInstructions, Global.MAX_MEMORY);
		return true;
	}

	/*@brief Memory is loaded with the inputted binary instructions, starting 
	*		  at 0x0000.
	* @param binaryInstructions An int[] of 8-bit binary values.
	* @return boolean True if successfuly created and memory is loaded. False if there exists values inside the array.
	*/
	public static boolean setMemory(int[] binaryInstructions){
		return true;
	}


	/*@brief Initializes a memory of size 65kb
	* 
	* @param None.
	* @return void.
	*/
	public static void write(int index, int value){
		return;
	}

	/*@brief Finds the binary instruction at the supplied index
	*
	* @param index 16-bit number of index in memory
	* @return 8-bit binary instruction
	*/
	public static int[] read(int index){
		return null;
	}

	/*@brief Finds all binary instructions between two indexes
	*
	* @param index1 The first index of the range
	* @param index2 The second index of the range
	* @return An array of indexes between index1 and index2, inclusive.
	*/
	public static int[] readRange(int index1, int index2){
		return null;
	}

	/*@brief Prints the array of binary instructions
	*
	* @param None.
	* @return Void.
	*/
	public String toString(){
		return Arrays.toString(memory);
	}

	/*@brief Prints the array of binary instructions between index1 and index2.
	*
	* @param index1 The first index of the range
	* @param index2 The second index of the range
	* @return Void.
	*/
	public String toString(int index){
		int[] tempArray = Arrays.copyOfRange(memory, index, memory.length);
		return Arrays.toString(tempArray); 
	}

	/*@brief replace each value with zero in Memory.
	*
	* @param None.
	* @return boolean True if successful.
	*/
	public static boolean clean(){
		return true;
	}



}