public class Instructions {

	public Instructions() {
	}

	/* C = Carry Flag
	*  Z = Zero Flag
	*  I = Interrupt disable/enable Flag
	*  D = Decimal Flag
	*  B = Break Flag
	*  V = Overflow Flag
	*  S = Sign Flag
	*/

/* Grouped by: http://www.obelisk.me.uk/6502/instructions.html


/* ====================== LOAD/STORE OPERATIONS ====================== */

/* ====================== REGISTER TRANSFERS ========================= */

/* ====================== STACK OPERATIONS =========================== */

/* ====================== LOGICAL OPERATIONS ========================= */

/* ====================== ARTHMETIC OPERATIONS ======================= */

/* ---------------------- ADC ---------------------- *
* @brief Add Memory to Accumulator with Carry
* Operation:  A + M + C -> A, C
* Flags Set:	N	Z	C	I	D	V
* 				+	+	+	-	-	+
*/
	//
	public static void ADC_IMM(int value8){}

	public static void ADC_ZP(int value8){}

	public static void ADC_ZPX(int value8){}

	public static void ADC_AB(int value16){}

	public static void ADC_ABX(int value16){}

	public static void ADC_ABY(int value16){}

	public static void ADC_INX(int value8){}

	public static void ADC_INY(int value8){}




/* ====================== INCREMENTS/DECREMENTS OPERATIONS =========== */

/* ====================== SHIFT OPERATIONS =========================== */

/* ====================== JUMPS/CALLS OPERATIONS ===================== */

/* ====================== BRANCHES OPERATIONS ======================== */

/* ====================== STATUS/FLAG OPERATIONS ===================== */

/* ======================  SYSTEM OPERATIONS ========================== */

	
}