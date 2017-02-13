package src;

public class Registers {

	private static int[] registers8;
	private static int[] registers16;

	/* @brief Initializes an array of 8-bit registers and a separate array of 16-bit registers
	*
	* @param None.
	* @return boolean True if successful.
	*/
	public boolean Registers(){
		registers8 = new int[4];
		registers16 = new int[2];
		return true;
	}

	/* @brief Read an 8-bit register.
	*
	* @param register Either $A, $X, $Y, $P
	* @return int 8-bit value
	*/
	public static int read8(int register){
		return 1;
	}

	/* @brief write a value to an 8-bit register.
	*
	* @param register Either $A, $X, $Y, $P
	* @param value 8-bit number
	* @return void.
	*/
	public static void write8(int register, int value){

	}

	/* @brief Read a 16-bit register.
	*
	* @param register Either $SP, $PC
	* @return int 16-bit value
	*/
	public static int read16(int register){
		return 1;
	}

	/* @brief write a value to an 16-bit register.
	*
	* @param register Either $SP, $PC
	* @param value 16-bit number
	* @return void.
	*/
	public static void write16(int register, int value){

	}

	/* @brief Check current state of flag in %P register.
	*
	* @param None.
	* @return boolean True if set.
	*/
	public static boolean isCarry(){
		return true;
	}

	public static boolean isZero(){
		return true;
	}

	public static boolean isIRQDisabled(){
		return true;
	}

	public static boolean isDecimal(){
		return true;
	}

	public static boolean isBreak(){
		return true;
	}

	public static boolean isOverflow(){
		return true;
	}

	public static boolean isNegative(){
		return true;
	}



	/* @brief Set specific flag to 1 (true) in $P register
	*
	* @param None.
	* @return boolean True if successfully set.
	*/
	public static boolean setCarry(){
		return true;
	}

	public static boolean setZero(){
		return true;
	}

	public static boolean setIRQDisabled(){
		return true;
	}

	public static boolean setDecimal(){
		return true;
	}

	public static boolean setBreak(){
		return true;
	}

	public static boolean setOverflow(){
		return true;
	}

	public static boolean setNegative(){
		return true;
	}



	/* @brief Set specific flag to 0 (false) in $P register.
	*
	* @param None.
	* @return boolean True if successfully set.
	*/
	public static boolean resetCarry(){
		return true;
	}

	public static boolean resetZero(){
		return true;
	}

	public static boolean resetIRQDisabled(){
		return true;
	}

	public static boolean resetDecimal(){
		return true;
	}

	public static boolean resetBreak(){
		return true;
	}

	public static boolean resetOverflow(){
		return true;
	}

	public static boolean resetNegative(){
		return true;
	}

	
}