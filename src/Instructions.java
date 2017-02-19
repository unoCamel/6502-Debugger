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
* ALSO: Header comments from: http://www.e-tradition.net/bytes/6502/6502_instruction_set.html
 */


/* ====================== LOAD/STORE OPERATIONS ====================== 
*These instructions transfer a single byte between memory and one of the 
*registers. Load operations set the negative (N) and zero (Z) flags depending
*on the value of transferred. Store operations do not affect the flag 
*settings.
*/

/* ---------------------- LDA ---------------------- *
* @brief Load Accumulator with Memory
* Operation:   M -> A
* Flags Set:	N	Z	C	I	D	V
* 				+	+	-	-	-	-
*/
	//0xA9
	public static void LDA_IMM(int value8){}
	//0xA5
	public static void LDA_ZP(int value8){}
	//0xB5
	public static void LDA_ZPX(int value8){}
	//0xAD
	public static void LDA_AB(int value16){}
	//0xBD
	public static void LDA_ABX(int value16){}
	//0xB9
	public static void LDA_ABY(int value16){}
	//0xA1
	public static void LDA_IDX(int value8){}
	//0xB1
	public static void LDA_IDY(int value8){}

/* ---------------------- LDX ---------------------- *
* @brief Load Index X with Memory
* Operation:   M -> X
* Flags Set:	N	Z	C	I	D	V
* 				+	+	-	-	-	-
*/
	//0xA2
	public static void LDX_IMM(int value8){}
	//0xA6
	public static void LDX_ZP(int value8){}
	//0xB6
	public static void LDX_ZPY(int value8){}
	//0xAE
	public static void LDX_AB(int value16){}
	//0xBE
	public static void LDX_ABY(int value16){}

/* ---------------------- LDY ---------------------- *
* @brief Load Index Y with Memory
* Operation:   M -> Y
* Flags Set:	N	Z	C	I	D	V
* 				+	+	-	-	-	-
*/
	//0xA0
	public static void LDY_IMM(int value8){}
	//0xA4
	public static void LDY_ZP(int value8){}
	//0xB4
	public static void LDY_ZPX(int value8){}
	//0xAC
	public static void LDY_AB(int value16){}
	//0xBC
	public static void LDY_ABX(int value16){}

/* ---------------------- STA ---------------------- *
* @brief Store Accumulator in Memory
* Operation:   A -> M
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	-	-
*/
	//0x85
	public static void STA_ZP(int value8){}
	//0x95
	public static void STA_ZPX(int value8){}
	//0x8D
	public static void STA_AB(int value16){}
	//0x9D
	public static void STA_ABX(int value16){}
	//0x99
	public static void STA_ABY(int value16){}
	//0x81
	public static void STA_IDX(int value8){}
	//0x91
	public static void STA_IDY(int value8){}

/* ---------------------- STX ---------------------- *
* @brief Store Index X in Memory
* Operation:   X -> M
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	-	-
*/
	//0x86
	public static void STX_ZP(int value8){}
	//0x96
	public static void STX_ZPY(int value8){}
	//0x8E
	public static void STX_AB(int value16){}

/* ---------------------- STY ---------------------- *
* @brief Store Index Y in Memory
* Operation:   Y -> M
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	-	-
*/
	//0x84
	public static void STY_ZP(int value8){}
	//0x94
	public static void STY_ZPX(int value8){}
	//0x8C
	public static void STY_AB(int value16){}

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

/* ====================== STACK OPERATIONS ===========================
*The 6502 microprocessor supports a 256 byte stack fixed between memory locations $0100 and $01FF.
* A special 8-bit register, S, is used to keep track of the next free byte of stack space.
* Pushing a byte on to the stack causes the value to be stored at the current free location (e.g. $0100,S)
* and then the stack pointer is post decremented. Pull operations reverse this procedure.
* The stack register can only be accessed by transferring its value to or from the X register.
* Its value is automatically modified by push/pull instructions, subroutine calls and returns, interrupts and returns from interrupts.
*/

/* ---------------------- TSX ---------------------- *
* @brief Transfer Stack Pointer to Index X
* Operation:  SP -> X
* Flags Set:	N	Z	C	I	D	V
* 				+	+	-	-	-	-
*/
    //0xBA
    public static void TSX_IMP(){}

/* ---------------------- TXS ---------------------- *
* @brief Transfer Index X to Stack Register
* Operation:  x -> SP
* Flags Set:	N	Z	C	I	D	V
* 				+	+	-	-	-	-
*/
    //0x9A
    public static void TXS_IMP(){}

/* ---------------------- PHA ---------------------- *
* @brief Push Accumulator on Stack
* Operation:  push A
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	-	-
*/
    //48
    public static void PHA_IMP(){}

/* ---------------------- PHP ---------------------- *
* @brief Push Processor Status on Stack
* Operation:  push SR
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	-	-
*/
    //0x08
    public static void PHP_IMP(){}

/* ---------------------- PLA ---------------------- *
* @brief Pull Accumulator from Stack
* Operation:  pull A
* Flags Set:	N	Z	C	I	D	V
* 				+	+	-	-	-	-
*/
    //0x68
    public static void PLA_IMP(){}

/* ---------------------- PLP ---------------------- *
* @brief Pull Processor Status from Stack
* Operation:  pull SR
* Flags Set:	N	Z	C	I	D	V
* 				flag results from stack
*/
    //0x28
    public static void PLP_IMP(){}

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
    public static void EOR_AB(int value16){}
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
	public static void ADC_IDX(int value8){}
    //0x71
	public static void ADC_IDY(int value8){}

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


/* ====================== INCREMENTS/DECREMENTS OPERATIONS =========== 
*Increment or decrement a memory location or one of the X or Y registers 
*by one setting the negative (N) and zero (Z) flags as appropriate.
*/

/* ---------------------- INC ---------------------- *
* @brief Increment Memory by One
* Operation:   M + 1 -> M
* Flags Set:	N	Z	C	I	D	V
* 				+	+	-	-	-	-
*/
	//0xE6
	public static void INC_ZP(int value8){}
	//0xF6
	public static void INC_ZPX(int value8){}
	//0xEE
	public static void INC_AB(int value16){}
	//0xFE
	public static void INC_ABX(int value16){}

/* ---------------------- INX ---------------------- *
* @brief Increment Index X by One
* Operation:   X + 1 -> X
* Flags Set:	N	Z	C	I	D	V
* 				+	+	-	-	-	-
*/
	//0xE8
	public static void INX_IMP(){}

/* ---------------------- INY ---------------------- *
* @brief Increment Index Y by One
* Operation:   Y + 1 -> Y
* Flags Set:	N	Z	C	I	D	V
* 				+	+	-	-	-	-
*/
	//0xC8
	public static void INY_IMP(){}

/* ---------------------- DEC ---------------------- *
* @brief Decrement Memory by One
* Operation:   M - 1 -> M
* Flags Set:	N	Z	C	I	D	V
* 				+	+	-	-	-	-
*/
	//0xC6
	public static void DEC_ZP(int value8){}
	//0xD6
	public static void DEC_ZPX(int value8){}
	//0xCE
	public static void DEC_AB(int value16){}
	//0xDE
	public static void DEC_ABX(int value16){}

/* ---------------------- DEX ---------------------- *
* @brief Decrement Index X by One
* Operation:   X - 1 -> X
* Flags Set:	N	Z	C	I	D	V
* 				+	+	-	-	-	-
*/
	//0xCA
	public static void DEX_IMP(){}

/* ---------------------- DEY ---------------------- *
* @brief Decrement Index Y by One
* Operation:   Y - 1 -> Y
* Flags Set:	N	Z	C	I	D	V
* 				+	+	-	-	-	-
*/
	//0x88
	public static void DEY_IMP(){}

/* ====================== SHIFT OPERATIONS =========================== 
*Shift instructions cause the bits within either a memory location or the 
*accumulator to be shifted by one bit position. The rotate instructions 
*use the contents if the carry flag (C) to fill the vacant position 
*generated by the shift and to catch the overflowing bit. The arithmetic 
*and logical shifts shift in an appropriate 0 or 1 bit as appropriate but 
*catch the overflow bit in the carry flag (C).
*/

/* ---------------------- ASL ---------------------- *
* @brief Shift Left One Bit (Memory or Accumulator)
* Operation:   C <- [76543210] <- 0 
* Flags Set:	N	Z	C	I	D	V
* 				+	+	+	-	-	-
*/
	//0x0A
	public static ASL_ACC(){}
	//0x06
	public static ASL_ZP(int value8){}
	//0x16
	public static ASL_ZPX(int value8){}
	//0x0E
	public static ASL_AB(int value16){}
	//0x1E
	public static ASL_ABX(int value16){}

/* ---------------------- LSR ---------------------- *
* @brief Shift One Bit Right (Memory or Accumulator)
* Operation:   0 -> [76543210] -> C
* Flags Set:	N	Z	C	I	D	V
* 				-	+	+	-	-	-
*/
	//0x4A
	public static LSR_ACC(){}
	//0x46
	public static LSR_ZP(int value8){}
	//0x56
	public static LSR_ZPX(int value8){}
	//0x4E
	public static LSR_AB(int value16){}
	//0x5E
	public static LSR_ABX(int value16){}

/* ---------------------- ROL ---------------------- *
* @brief Rotate One Bit Left (Memory or Accumulator)
* Operation:   C <- [76543210] <- C 
* Flags Set:	N	Z	C	I	D	V
* 				+	+	+	-	-	-
*/
	//0x2A
	public static ROL_ACC(){}
	//0x26
	public static ROL_ZP(int value8){}
	//0x36
	public static ROL_ZPX(int value8){}
	//0x2E
	public static ROL_AB(int value16){}
	//0x3E
	public static ROL_ABX(int value16){}

/* ---------------------- ROR ---------------------- *
* @brief Rotate One Bit Right (Memory or Accumulator)
* Operation:   C -> [76543210] -> C 
* Flags Set:	N	Z	C	I	D	V
* 				+	+	+	-	-	-
*/
	//0x6A
	public static ROR_ACC(){}
	//0x66
	public static ROR_ZP(int value8){}
	//0x76
	public static ROR_ZPX(int value8){}
	//0x6E
	public static ROR_AB(int value16){}
	//0x7E
	public static ROR_ABX(int value16){}


/* ====================== JUMPS/CALLS OPERATIONS =====================
* The following instructions modify the program counter causing a break to normal sequential execution.
* The JSR instruction pushes the old PC onto the stack before changing it to the new location allowing
* a subsequent RTS to return execution to the instruction after the call.
 */

/* ---------------------- JMP ---------------------- *
* @brief Jump to a New Location
* Operation:   (PC+1) -> PCL
* Operation:   (PC+2) -> PCH
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	-	-
*/
    //0x4c
    public static void JMP_AB(int value16){}
    //0x6C
    public static void JMP_ID(int value8){} //TODO CHECK

/* ---------------------- JSR ---------------------- *
* @brief Jump to New Location Saving Return Address
* Operation:   (push (PC+2), then either
* Operation:   (PC+1) -> PCL
* Operation:   (PC+2) -> PCH
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	-	-
*/

    //0x20
    public static void JSR_AB(int value16){}

/* ---------------------- RTS ---------------------- *
* @brief Return from Subroutine
* Operation:   pull PC, then
* Operation:   PC+1 -> PC
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	-	-
*/

    //0x60
    public static void RTS_IMP(){}

/* ====================== BRANCHES OPERATIONS ======================== 
*Branch instructions break the normal sequential flow of execution by changing
*the program counter if a specified condition is met. All the conditions 
*are based on examining a single bit within the processor status.
*/

/* ---------------------- BCC ---------------------- *
* @brief Branch on Carry Clear
* Operation:   branch on C = 0 
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	-	-
*/
	//0x90
	public static BCC_REL(){}

/* ---------------------- BCS ---------------------- *
* @brief Branch on Carry Set
* Operation:   branch on C = 1
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	-	-
*/
	//0xB0
	public static BCS_REL(){}

/* ---------------------- BEQ ---------------------- *
* @brief Branch on Result Zero
* Operation:   branch on Z = 1
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	-	-
*/
	//0xF0
	public static BEQ_REL(){}

/* ---------------------- BMI ---------------------- *
* @brief Branch on Result Minus
* Operation:   branch on N = 1
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	-	-
*/
	//0x30
	public static BMI_REL(){}

/* ---------------------- BNE ---------------------- *
* @brief Branch on Result not Zero
* Operation:   branch on Z = 0
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	-	-
*/
	//0xD0
	public static BNE_REL(){}

/* ---------------------- BPL ---------------------- *
* @brief Branch on Result not Zero
* Operation:   branch on N = 0
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	-	-
*/
	//0x10
	public static BPL_REL(){}

/* ---------------------- BVC ---------------------- *
* @brief Branch on Overflow Clear
* Operation:   branch on V = 0
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	-	-
*/
	//0x50
	public static BVC_REL(){}

/* ---------------------- BVS ---------------------- *
* @brief Branch on Overflow Set
* Operation:   branch on V = 1
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	-	-
*/
	//0x50
	public static BVS_REL(){}

/* ====================== STATUS/FLAG OPERATIONS =====================
* The following instructions change the values of specific status flags.
*/

/* ---------------------- CLC ---------------------- *
* @brief Clear Carry Flag
* Operation:   0 -> C
* Flags Set:	N	Z	C	I	D	V
* 				-	-	0	-	-	-
*/
    //0x18
    public static void CLC_IMP(){}

/* ---------------------- CLD ---------------------- *
* @brief Clear Decimal Mode
* Operation:   0 -> C
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	0	-
*/
    //0xD8
    public static void CLD_IMP(){}

/* ---------------------- CLI ---------------------- *
* @brief Clear Interrupt Disable Bit
* Operation:   0 -> I
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	0	-	-
*/
    //0x58
    public static void CLI_IMP(){}

/* ---------------------- CLV ---------------------- *
* @brief Clear Overflow Flag
* Operation:   0 -> V
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	-	0
*/
    //0xB8
    public static void CLV_IMP(){}

/* ---------------------- SEC ---------------------- *
* @brief Set Carry Flag
* Operation:   1 -> C
* Flags Set:	N	Z	C	I	D	V
* 				-	-	1	0	-	-
*/
    //0x38
    public static void SEC_IMP(){}

/* ---------------------- SED ---------------------- *
* @brief Set Decimal Flag
* Operation:   1 -> D
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	1	-
*/
    //0xF8
    public static void SED_IMP(){}

/* ---------------------- SEI ---------------------- *
* @brief Set Interrupt Disable Status
* Operation:   1 -> I
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	1	-	-
*/
    //0x78
    public static void SEI_IMP(){}

/* ======================  SYSTEM OPERATIONS ==========================
* Functions focused on interrupts, and then the No-operation function.
*/

/* ---------------------- BRK ---------------------- *
* @brief Force an interrupt
* Operation:   interrupt, then
* Operation:    push PC+2, push SR
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	1	-	-
*/
    //0x00
    public static void BRK_IMP(){}

/* ---------------------- NOP ---------------------- *
* @brief No-Operation
* Operation: None.
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	-	-
*/
    //0xEA
    public static void NOP_IMP(){}

/* ---------------------- RTI ---------------------- *
* @brief Return from interrupt
* Operation: pull SR, pull PC
* Flags Set:	N	Z	C	I	D	V
* 				info comes from stack
*/
    //0x40
    public static void RTI_IMP(){}
	
}