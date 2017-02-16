public class ALU {


	/* C = Carry Flag
	*  Z = Zero Flag
	*  I = Interrupt disable/enable Flag
	*  D = Decimal Flag
	*  B = Break Flag
	*  V = Overflow Flag
	*  S = Sign Flag
	*/


	//TODO: Function headers for the rest of the ALU
	// Fix ADD and OR, make sure it is correct.



	public ALU(){}

	/* @brief Performs the operation A + M + C -> A, C
	* Add memory to accumulator with carry.
	* Flags set as follows:
	* C: If borrow required, set to 0. 
	* Z: Set if result is zero. Reset if not-zero.
	* I: Not Set.
	* D: Check if set, if set, perform BCD arithmetic.
	* V: Not Set.
	* S: Set if number is greater than 255 or 99 in decimal mode.
	* Negative: 
	* @param reg1 8-Bit register, either $A, $X, $Y
	* @param reg2 8-Bit register, either $A, $X, $Y
	* @return 8-Bit number, the result, as well as any needed changes to the flags.
	*/
	public int ADD(int reg1, int reg2){
		return -1;
	}

	/* @brief Performs the opeation A | B -> A
	* Flags set as follows:
	* C: Not Set.
	* Z: Set if result is zero. Reset if not-zero.
	* I: Not Set.
	* D: Not Set.
	* V: Not Set.
	* S: Not Set.
	* Negative: 
	* @param reg1 8-Bit register, either $A, $X, $Y
	* @param reg2 8-Bit register, either $A, $X, $Y
	* @return 8-Bit number, the result, as well as any needed changes to the flags.
	*/
	public int OR(int reg1, int reg2){
		return -1;
	}

	public int XOR(int reg1, int reg2){
		return -1;
	}

	public int AND(int reg1, int reg2){
		return -1;
	}

	public int SR(int reg1, int reg2){
		return -1;
	}






}