import java.util.Arrays;

public class Memory {

	private int[] memory;


	/* @brief Initializes a memory of size 65kb
	* 
	* @param None.
	* @return boolean True if successfuly created
	*/
	public boolean Memory(){
		memory = new int[MAX_MEMORY];

		boolean test;
		return test;
	}

	/* @brief Initializes a memory of size 65kb, and fills the memory with the inputted
	*         list of binary instructions.
	* @param binaryInstructions An int[] of 8-bit binary values.
	* @return boolean True if successfuly created and memory is loaded
	*/
	public boolean Memory(int[] binaryInstructions){
		memory = Arrays.copyOf(binaryInstructions, MAX_MEMORY);

		boolean test;
		return test;
	}

	/* @brief Memory is loaded with the inputted binary instructions, starting 
	*		  at 0x0000.
	* @param binaryInstructions An int[] of 8-bit binary values.
	* @return boolean True if successfuly created and memory is loaded. False if there exists values inside the array.
	*/
	public boolean setMemory(int[] binaryInstructions){
		boolean test;
		return test;
	}


	/* @brief Initializes a memory of size 65kb
	* 
	* @param None.
	* @return boolean True if successfuly created
	*/
	public boolean write(int index, int value){
		boolean test;
		return test;
	}

	/* @brief Finds the binary instruction at the supplied index
	*
	* @param index 16-bit number of index in memory
	* @return 8-bit binary instruction
	*/
	public int[] read(int index){
		int[] test;
		return test;
	}

	/* @brief Finds all binary instructions between two indexes
	*
	* @param index1 The first index of the range
	* @param index2 The second index of the range
	* @return An array of indexes between index1 and index2, inclusive.
	*/
	public int[] readRange(int index1, int index2){
		int[] test;
		return test;
	}

	/* @brief Prints the array of binary instructions
	*
	* @param None.
	* @return Void.
	*/
	public String toString(){
		return Arrays.toString(memory);
	}

	/* @brief Prints the array of binary instructions between index1 and index2.
	*
	* @param index1 The first index of the range
	* @param index2 The second index of the range
	* @return Void.
	*/
	public String toString(int index){
		int[] tempArray = Arrays.copyOfRange(memory, index, memory.length);
		return Arrays.toString(tempArray); 
	}

	/* @brief replace each value with zero in Memory.
	*
	* @param None.
	* @return boolean True if successful.
	*/
	public boolean clean(){
		boolean test;
		return test;
	}



}