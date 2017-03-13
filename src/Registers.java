import java.util.Arrays;
public class Registers {

	private static int[] registers8 = new int[4];
	private static int[] registers16 = new int[2];
	private static int PC;
	private static int SP;

	/* @brief Initializes an array of 8-bit registers and a separate array of 16-bit registers
	*
	* @param None.
	* @return boolean True if successful.
	*/
	public Registers(){}

	/* @brief Returns current state of PC
	*
	*@params None.
	*@return 16 b
	*/
	public static int readPC(){ return PC; }

	/* @brief Writes a 16-bit value to the current $PC. For use by branch and jump instructions.
	*
	*@params value 16-bit integer.
	*@return Void.
	*/
	public static void writePC(int value){ PC = value; }

	/* @brief Increment the current $PC by the instruction. Not for use by branch or jump instructions.
	*
	*@params increment 8bit int.
	*@return Void.
	*/
	public static void incrPC(int increment){ PC += increment; }

	/* @brief Returns current state of SP
	*
	*@params None.
	*@return 8 bit value
	*/
	public static int readSP(){ return SP; }

	/* @brief Writes a 8-bit value to the current $SP. For use by branch and jump instructions.
	*
	*@params value 16-bit integer.
	*@return Void.
	*/
	public static void writeSP(int value){ SP = value; }

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
	public static void write8(int register, int value){
	    if(value >= 0 && value <= 0xFF){
            registers8[register] = value;
        } else {
	        throw new Error("Value is not 8-bits: " + value);
        }
	}

	public static void init_Memory(){
		Arrays.fill(registers8, 0);
		writePC(0x0200);
		writeSP(0xFF);
	}


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
	public static void write16(int register, int value) {
        if (value >= 0 && value <= 0xFFFF) {
            registers16[register] = value;
        } else {
            throw new Error("Value is not 16-bits: " + value);
        }
    }

	/* @brief Check current state of flag in %P register.
	*
	* @param None.
	* @return boolean True if set.
	*/
	public static boolean isCarry()			{return ((registers8[Global.$P]) & 0b0000_0001) != 0;}
	public static boolean isZero()			{return ((registers8[Global.$P] & 0b0000_0010) != 0);} //Checking individual bit by shifting bit down
	public static boolean isIRQDisabled()	{return ((registers8[Global.$P] & 0b0000_0100) != 0);} //Then checking the first bit
	public static boolean isDecimal()		{return ((registers8[Global.$P] & 0b0000_1000) != 0);} //Then converting bit into boolean 1 = True
	public static boolean isBreak()			{return ((registers8[Global.$P] & 0b0001_0000) != 0);} //Sorry for C code.
	public static boolean isOverflow()		{return ((registers8[Global.$P] & 0b0100_0000) != 0);}
	public static boolean isNegative()		{return ((registers8[Global.$P] & 0b1000_0000) != 0);}

	/* @brief Set specific flag to 1 (true) in $P register
	*
	* @param None.
	* @return boolean True if successfully set.
	*/
	public static void setCarry()			{registers8[Global.$P] |= 0b0000_0001;} //Just replace the singular bit to 1, if not already.
	public static void setZero()			{registers8[Global.$P] |= 0b0000_0010;} //This will ignore the rest of the bits
	public static void setIRQDisabled()		{registers8[Global.$P] |= 0b0000_0100;} //So other bits will not get overwritten.
	public static void setDecimal()			{registers8[Global.$P] |= 0b0000_1000;}
	public static void setBreak()			{registers8[Global.$P] |= 0b0001_0000;}
	public static void setOverflow()		{registers8[Global.$P] |= 0b0100_0000;}
	public static void setNegative()		{registers8[Global.$P] |= 0b1000_0000;}

	/* @brief Set specific flag to 0 (false) in $P register.
	*
	* @param None.
	* @return boolean True if successfully set.
	*/
	public static void resetCarry()			{registers8[Global.$P] &= ~(0b0000_0001);}
	public static void resetZero()			{registers8[Global.$P] &= ~(0b0000_0010);} //Flip the bit 1 at the shifted bit
	public static void resetIRQDisabled()	{registers8[Global.$P] &= ~(0b0000_0100);} //This is shifting the selected bit
	public static void resetDecimal()		{registers8[Global.$P] &= ~(0b0000_1000);} //To the first position bit.
	public static void resetBreak()			{registers8[Global.$P] &= ~(0b0001_0000);}
	public static void resetOverflow()		{registers8[Global.$P] &= ~(0b0100_0000);}
	public static void resetNegative()		{registers8[Global.$P] &= ~(0b1000_0000);}

	public static void currentState(){
		System.out.print("Current State of Processor at: "+ Integer.toHexString(readPC()));
		if(Memory.read(Registers.readPC()) != null) {
			System.out.print(" Instr: " + Integer.toHexString(Memory.read(Registers.readPC())));
		}
		System.out.println("\n$A: " + Integer.toHexString(read8(Global.$A))
							+ " $X: " + Integer.toHexString(read8(Global.$X))
							+ " $Y: " + Integer.toHexString(read8(Global.$Y))
							+ " $SP: " + Integer.toHexString(readSP())
							+ " $P: " + Integer.toBinaryString(read8(Global.$P)));
		System.out.println();
		
	}

	public static String registersToString(){
	    String registerString = "";
	    registerString = registerString.concat(" $PC: " + String.format(" "+"%04x", (int) readPC()).toUpperCase() + "    " + "$SP:   " + String.format(" "+"%02x", (int) readSP()).toUpperCase() + "\n");
        registerString = registerString.concat(" $A:    " + String.format(" "+"%02x", (int) read8(Global.$A)).toUpperCase() + "\n");
        registerString = registerString.concat(" $X:    " + String.format(" "+"%02x", (int) read8(Global.$X)).toUpperCase() + "\n");
        registerString = registerString.concat(" $Y:    " + String.format(" "+"%02x", (int) read8(Global.$Y)).toUpperCase() + "\n");
        registerString = registerString.concat(" $P:   0b" + String.format(""+"%08d",  Integer.parseInt(Integer.toBinaryString(read8(Global.$P)))) + "\n");
        registerString = registerString.concat("Previous Instruction:");
        String prevInstr = (String)DebuggerGUI.prevInstructionString;
        String curInstr = (String)DebuggerGUI.instructionString;
        if(prevInstr.equals(curInstr)){
            registerString = registerString.concat("\n \n");
        }else{
            registerString = registerString.concat("\n" + prevInstr);
        }
        registerString = registerString.concat("Current Instruction: \n");
        registerString = registerString.concat(curInstr);



	    return registerString;
    }

	
}