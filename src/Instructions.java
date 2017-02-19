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

/* ====================== LOGICAL OPERATIONS =========================
*The following instructions perform logical operations on the contents of the accumulator and another value held in memory.
* The BIT instruction performs a logical AND to test the presence of bits in the memory value to set the flags but does not keep the result.
 */

/* ---------------------- AND ---------------------- *
* @brief AND Memory with Accumulator
* Operation:  A AND M -> A
* Flags Set:	N	Z	C	I	D	V
* 				+	+	-	-	-	-
*/
    //0x29
    public static void AND_IMM(int value8){}
    //0x25
    public static void AND_ZP(int value8){}
    //0x35
    public static void AND_ZPX(int value8){}
    //0x2D
    public static void AND_AB(int value16){}
    //0x3D
    public static void AND_ABX(int value16){}
    //0x39
    public static void AND_ABY(int value16){}
    //0x21
    public static void AND_IDX(int value8){}
    //0x31
    public static void AND_IDY(int value8){}

/* ---------------------- EOR ---------------------- *
* @brief Exclusive-OR Memory with Accumulator
* Operation:  A EOR M -> A
* Flags Set:	N	Z	C	I	D	V
* 				+	+	-	-	-	-
*/
    //0x49
    public static void EOR_IMM(int value8){}
    //0x45
    public static void EOR_ZP(int value8){}
    //0x55
    public static void EOR_ZPX(int value8){}
    //0x4D
    public static void EOR_ABS(int value16){}
    //0x5D
    public static void EOR_ABX(int value16){}
    //0x59
    public static void EOR_ABY(int value16){}
    //0x41
    public static void EOR_IDX(int value8){}
    //0x51
    public static void EOR_IDY(int value8){}

/* ---------------------- ORA ---------------------- *
* @brief OR Memory with Accumulator
* Operation:  A OR M -> A
* Flags Set:	N	Z	C	I	D	V
* 				+	+	-	-	-	-
*/
    //0x09
    public static void ORA_IMM(int value8){}
    //0x05
    public static void ORA_ZP(int value8){}
    //0x15
    public static void ORA_ZPX(int value8){}
    //0x0D
    public static void ORA_AB(int value16){}
    //0x1D
    public static void ORA_ABX(int value16){}
    //0x19
    public static void ORA_ABY(int value16){}
    //0x01
    public static void ORA_IDX(int value8){}
    //0x11
    public static void ORA_IDY(int value8){}

/* ---------------------- BIT ---------------------- *
* @brief Test Bits in Memory with Accumulator bits 7 and 6 of operand are transferred to bit 7 and 6 of SR (N,V);
* the zeroflag is set to the result of operand AND accumulator.
* Operation:  A AND M, M7 -> N, M6 -> V
* Flags Set:	N	Z	C	I	D	V
* 				M7	+	-	-	-	M6
*/
    //0x24
    public static void BIT_ZP(int value8){}
    //0x2C
    public static void BIT_AB(int value16){}

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