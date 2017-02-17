public class Registers {

	private static int[] registers8;
	private static int[] registers16;

	/* @brief Initializes an array of 8-bit registers and a separate array of 16-bit registers
	*
	* @param None.
	* @return boolean True if successful.
	*/
	public Registers(){
		registers8 = new int[4];
		registers16 = new int[2];
	}

	/* @brief Read an 8-bit register.
	*
	* @param register Either $A, $X, $Y, $P
	* @return int 8-bit value
	*/
	public static int read8(int register){ return registers8[register]; }

	/* @brief write a value to an 8-bit register.
	*
	* @param register Either $A, $X, $Y, $P
	* @param value 8-bit number
	* @return void.
	*/
	public static void write8(int register, int value){ registers8[register] = value; }

	/* @brief Read a 16-bit register.
	*
	* @param register Either $SP, $PC
	* @return int 16-bit value
	*/
	public static int read16(int register){ return registers16[register]; }

	/* @brief write a value to an 16-bit register.
	*
	* @param register Either $SP, $PC
	* @param value 16-bit number
	* @return void.
	*/
	public static void write16(int register, int value){ registers16[register] = value; }

	/* @brief Check current state of flag in %P register.
	*
	* @param None.
	* @return boolean True if set.
	*/
	public static boolean isCarry()			{return (registers8[Global.$P]) != 0;}
	public static boolean isZero()			{return (((registers8[Global.$P] >> 0x01) & 0x01) != 0);} //Checking individual bit by shifting bit down
	public static boolean isIRQDisabled()	{return (((registers8[Global.$P] >> 0x02) & 0x01) != 0);} //Then checking the first bit
	public static boolean isDecimal()		{return (((registers8[Global.$P] >> 0x03) & 0x01) != 0);} //Then converting bit into boolean 1 = True
	public static boolean isBreak()			{return (((registers8[Global.$P] >> 0x04) & 0x01) != 0);} //Sorry for C code.
	public static boolean isOverflow()		{return (((registers8[Global.$P] >> 0x06) & 0x01) != 0);}
	public static boolean isNegative()		{return (((registers8[Global.$P] >> 0x07) & 0x01) != 0);}

	/* @brief Set specific flag to 1 (true) in $P register
	*
	* @param None.
	* @return boolean True if successfully set.
	*/
	public static void setCarry()			{registers8[Global.$P] |= 0x01;} //Just replace the singular bit to 1, if not already.
	public static void setZero()			{registers8[Global.$P] |= 0x02;} //This will ignore the rest of the bits
	public static void setIRQDisabled()		{registers8[Global.$P] |= 0x03;} //So other bits will not get overwritten.
	public static void setDecimal()			{registers8[Global.$P] |= 0x04;}
	public static void setBreak()			{registers8[Global.$P] |= 0x05;}
	public static void setOverflow()		{registers8[Global.$P] |= 0x07;}
	public static void setNegative()		{registers8[Global.$P] |= 0x08;}

	/* @brief Set specific flag to 0 (false) in $P register.
	*
	* @param None.
	* @return boolean True if successfully set.
	*/
	public static void resetCarry()			{registers8[Global.$P] &= ~(0x01);}
	public static void resetZero()			{registers8[Global.$P] &= ~(0x01 << 0x01);} //Flip the bit 1 at the shifted bit
	public static void resetIRQDisabled()	{registers8[Global.$P] &= ~(0x01 << 0x02);} //This is shifting the selected bit
	public static void resetDecimal()		{registers8[Global.$P] &= ~(0x01 << 0x03);} //To the first position bit.
	public static void resetBreak()			{registers8[Global.$P] &= ~(0x01 << 0x04);}
	public static void resetOverflow()		{registers8[Global.$P] &= ~(0x01 << 0x06);}
	public static void resetNegative()		{registers8[Global.$P] &= ~(0x01 << 0x07);}

	
}