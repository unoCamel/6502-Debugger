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
	public int read8(int register){
		return 1;
	}

	/* @brief write a value to an 8-bit register.
	*
	* @param register Either $A, $X, $Y, $P
	* @param value 8-bit number
	* @return boolean True if successful.
	*/
	public boolean write8(int register, int value){
		return true;
	}

	/* @brief Read a 16-bit register.
	*
	* @param register Either $SP, $PC
	* @return int 16-bit value
	*/
	public int read16(int register){
		return 1;
	}

	/* @brief write a value to an 16-bit register.
	*
	* @param register Either $SP, $PC
	* @param value 16-bit number
	* @return boolean True if successful.
	*/
	public boolean write16(int register, int value){
		return true;
	}

	/* @brief Check current state of flag in %P register.
	*
	* @param None.
	* @return boolean True if set.
	*/
	public boolean isCarry(){
		return true;
	}

	public boolean isZero(){
		return true;
	}

	public boolean isIRQDisabled(){
		return true;
	}

	public boolean isDecimal(){
		return true;
	}

	public boolean isBreak(){
		return true;
	}

	public boolean isOverflow(){
		return true;
	}

	public boolean isNegative(){
		return true;
	}



	/* @brief Set specific flag to 1 (true) in $P register
	*
	* @param None.
	* @return boolean True if successfully set.
	*/
	public boolean setCarry(){
		return true;
	}

	public boolean setZero(){
		return true;
	}

	public boolean setIRQDisabled(){
		return true;
	}

	public boolean setDecimal(){
		return true;
	}

	public boolean setBreak(){
		return true;
	}

	public boolean setOverflow(){
		return true;
	}

	public boolean setNegative(){
		return true;
	}



	/* @brief Set specific flag to 0 (false) in $P register.
	*
	* @param None.
	* @return boolean True if successfully set.
	*/
	public boolean resetCarry(){
		return true;
	}

	public boolean resetZero(){
		return true;
	}

	public boolean resetIRQDisabled(){
		return true;
	}

	public boolean resetDecimal(){
		return true;
	}

	public boolean resetBreak(){
		return true;
	}

	public boolean resetOverflow(){
		return true;
	}

	public boolean resetNegative(){
		return true;
	}

	
}