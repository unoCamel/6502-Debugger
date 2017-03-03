public class ALU {

	/* C = Carry Flag
	*  Z = Zero Flag
	*  I = Interrupt disable/enable Flag
	*  D = Decimal Flag
	*  B = Break Flag
	*  V = Overflow Flag
	*  N = Sign Flag
	*/

	public ALU() {
	}

	/* @brief Performs the operation A + M + C -> A, C
	* Add memory to accumulator with carry.
	* Flags set as follows:
	* C: If borrow required, set to 0. 
	* Z: Set if result is zero. Reset if not-zero.
	* I: Not Set.
	* D: Check if set, if set, perform BCD arithmetic.
	* V: Set if number is greater than 255 or 99 in decimal mode.
	* N: Set if less than 0.
	* @param reg1 8-Bit register, either $A, $X, $Y
	* @param reg2 8-Bit register, either $A, $X, $Y
	* @return 8-Bit number, the result, as well as any needed changes to the flags.
	*/
	public static int ADD(int value1, int value2) {
		int temp = value1 + value2;
		if (temp < 0 || temp > 255) {
			Registers.setCarry(); //set overflow flag
			temp &= 0xFF;
			Registers.setOverflow();
		}
		if (temp == 0) {
			Registers.setZero();
		} else {
			Registers.resetZero();
		}
		//decimal check here!!!!!
		return temp;
	}

	/* @brief Performs the operation A | B -> A
	* Flags set as follows:
	* C: Not Set.
	* Z: Set if result is zero. Reset if not-zero.
	* I: Not Set.
	* D: Not Set.
	* V: Not Set.
	* N: Not Set.
	* @param reg1 8-Bit register, either $A, $X, $Y
	* @param reg2 8-Bit register, either $A, $X, $Y
	* @return 8-Bit number, the result, as well as any needed changes to the flags.
	*/
	public static int OR(int value1, int value2) {
		int temp = value1 | value2;
		if (temp == 0) {
			Registers.setZero();
		} else {
			Registers.resetZero();
		}
		return temp;
	}

	/* @brief Performs the operation A ^ B -> A
	* Flags set as follows:
	* C: Not Set.
	* Z: Set if result is zero. Reset if not-zero.
	* I: Not Set.
	* D: Not Set.
	* V: Not Set.
	* N: Not Set.
	* @param reg1 8-Bit register, either $A, $X, $Y
	* @param reg2 8-Bit register, either $A, $X, $Y
	* @return 8-Bit number, the result, as well as any needed changes to the flags.
	*/
	public static int XOR(int value1, int value2) {
		int temp = value1 ^ value2;
		if (temp == 0) {
			Registers.setZero();
		} else {
			Registers.resetZero();
		}
		return temp;
	}

	/* @brief Performs the operation A & B -> A
	* Flags set as follows:
	* C: Not Set.
	* Z: Set if result is zero. Reset if not-zero.
	* I: Not Set.
	* D: Not Set.
	* V: Not Set.
	* N: Not Set.
	* @param reg1 8-Bit register, either $A, $X, $Y
	* @param reg2 8-Bit register, either $A, $X, $Y
	* @return 8-Bit number, the result, as well as any needed changes to the flags.
	*/
	public static int AND(int value1, int value2) {
		int temp = value1 & value2;
		if (temp == 0) {
			Registers.setZero();
		} else {
			Registers.resetZero();
		}
		return temp;
	}

	/* @brief Performs the operation A >> B -> A
	* Flags set as follows:
	* C: Not Set.
	* Z: Set if result is zero. Reset if not-zero.
	* I: Not Set.
	* D: Not Set.
	* V: Not Set.
	* N: Not Set.
	* @param reg1 8-Bit register, either $A, $X, $Y
	* @param reg2 8-Bit register, either $A, $X, $Y
	* @return 8-Bit number, the result, as well as any needed changes to the flags.
	*/
	public static int SR(int value1, int value2) {
		int temp = value1 >> value2;
		if (temp == 0) {
			Registers.setZero();
		} else {
			Registers.resetZero();
		}
		return temp;
	}


	/*====================Helper Functions=====================*/

}





