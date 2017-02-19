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

/* ====================== REGISTER TRANSFERS =========================
*The contents of the X and Y registers can be moved to or from the accumulator,
*setting the negative (N) and zero (Z) flags as appropriate.
*/

/* ---------------------- TAX ---------------------- *
* @brief Transfer Accumulator to Index X
* Operation:   A -> X
* Flags Set:	N	Z	C	I	D	V
* 				+	+	-	-	-	-
*/
    //0xAA
    public static void TAX_IMP(){}

/* ---------------------- TAY ---------------------- *
* @brief Transfer Accumulator to Index Y
* Operation:   A -> Y
* Flags Set:	N	Z	C	I	D	V
* 				+	+	-	-	-	-
*/
    //0xA8
    public static void TAY_IMP(){}

/* ---------------------- TXA ---------------------- *
* @brief Transfer Index X to Accumulator
* Operation:   X -> A
* Flags Set:	N	Z	C	I	D	V
* 				+	+	-	-	-	-
*/
    //0x8A
    public static void TXA_IMP(){}

/* ---------------------- TYA ---------------------- *
* @brief Transfer Index Y to Accumulator
* Operation:   Y -> A
* Flags Set:	N	Z	C	I	D	V
* 				+	+	-	-	-	-
*/

    //0x98
    public static void TYA_IMP(){}

/* ====================== STACK OPERATIONS =========================== */

/* ====================== LOGICAL OPERATIONS ========================= */

/* ====================== ARITHMETIC OPERATIONS =======================
*The arithmetic operations perform addition and subtraction on the contents of the accumulator.
* The compare operations allow the comparison of the accumulator and X or Y with memory values.
 */

/* ---------------------- ADC ---------------------- *
* @brief Add Memory to Accumulator with Carry
* Operation:  A + M + C -> A, C
* Flags Set:	N	Z	C	I	D	V
* 				+	+	+	-	-	+
*/
    //0x69
	public static void ADC_IMM(int value8){}
    //0x65
	public static void ADC_ZP(int value8){}
    //0x75
	public static void ADC_ZPX(int value8){}
	//0x6D
	public static void ADC_AB(int value16){}
    //0x7D
	public static void ADC_ABX(int value16){}
    //0x79
	public static void ADC_ABY(int value16){}
    //0x61
	public static void ADC_INX(int value8){}
    //0x71
	public static void ADC_INY(int value8){}

/* ---------------------- SBC ---------------------- *
* @brief Subtract Memory from Accumulator with Borrow
* Operation:   A - M - C -> A
* Flags Set:	N	Z	C	I	D	V
* 				+	+	+	-	-	+
*/
    //0xE9
    public static void SBC_IMM(int value8){}
    //0xE5
    public static void SBC_ZP(int value8){}
    //0xF5
    public static void SBC_ZPX(int value8){}
    //0xED
    public static void SBC_AB(int value16){}
    //0xFD
    public static void SBC_ABX(int value16){}
    //0xF9
    public static void SBC_ABY(int value16){}
    //0xE1
    public static void SBC_IDX(int value8){}
    //0xF1
    public static void SBC_IDY(int value8){}

/* ---------------------- CMP ---------------------- *
* @brief Compare Memory with Accumulator
* Operation:   A - M
* Flags Set:	N	Z	C	I	D	V
* 				+	+	+	-	-	-
*/
    //0xC9
    public static void CMP_IMM(int value8){}
    //0xC5
    public static void CMP_ZP(int value8){}
    //0xD5
    public static void CMP_ZPX(int value8){}
    //0xCD
    public static void CMP_AB(int value16){}
    //0xDD
    public static void CMP_ABX(int value16){}
    //0xD9
    public static void CMP_ABY(int value16){}
    //0xC1
    public static void CMP_IDX(int value8){}
    //D1
    public static void CMP_IDY(int value8){}

/* ---------------------- CPX ---------------------- *
* @brief Compare Memory and Index X
* Operation:   X - M
* Flags Set:	N	Z	C	I	D	V
* 				+	+	+	-	-	-
*/
    //0xE0
    public static void CPX_IMM(int value8){}
    //0xE4
    public static void CPX_ZP(int value8){}
    //0xEC
    public static void CPX_AB(int value16){}

/* ---------------------- CPY ---------------------- *
* @brief Compare Memory and Index Y
* Operation:   Y - M
* Flags Set:	N	Z	C	I	D	V
* 				+	+	+	-	-	-
*/
    //0xC0
    public static void CPY_IMM(int value8){}
    //0xC4
    public static void CPY_ZP(int value8){}
    //0xCC
    public static void CPY_AB(int value16){}


/* ====================== INCREMENTS/DECREMENTS OPERATIONS =========== */

/* ====================== SHIFT OPERATIONS =========================== */

/* ====================== JUMPS/CALLS OPERATIONS ===================== */

/* ====================== BRANCHES OPERATIONS ======================== */

/* ====================== STATUS/FLAG OPERATIONS ===================== */

/* ======================  SYSTEM OPERATIONS ========================== */

	
}