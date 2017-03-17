public class Instructions {

	public Instructions() {}

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
	//0xA9 Load value8 into the accumulator
	public static void LDA_IMM(int value8){Registers.write8(Global.$A, value8); 
	checkArithmeticFlags(Registers.read8(Global.$A));}
	//0xA5 Load ZeroPage[value8] into the accumulator // calling Memory.read will do that
	public static void LDA_ZP(int value8){Registers.write8(Global.$A, Memory.read(value8)); 
	checkArithmeticFlags(Registers.read8(Global.$A));}
	//0xB5 Load ZeroPage[value8 + X] into the accumulator
	public static void LDA_ZPX(int value8){Registers.write8(Global.$A, Memory.read( ALU.ADD(value8, Registers.read8(Global.$X))));
	checkArithmeticFlags(Registers.read8(Global.$A));}
	//0xAD Load value16 into the accumulator
	public static void LDA_AB(int value16){Registers.write8(Global.$A, Memory.read(value16)); 
	checkArithmeticFlags(Registers.read8(Global.$A));}
	//0xBD Load (value16 + X) into the accumulator
	public static void LDA_ABX(int value16){Registers.write8(Global.$A, Memory.read( ALU.ADD(value16,Registers.read8(Global.$X))));
	checkArithmeticFlags(Registers.read8(Global.$A));}
	//0xB9 Load (value16 + Y) into the accumulator
	public static void LDA_ABY(int value16){Registers.write8(Global.$A, Memory.read( ALU.ADD(value16,Registers.read8(Global.$Y))));
	checkArithmeticFlags(Registers.read8(Global.$A));}
	//0xA1
	public static void LDA_IDX(int value8){Registers.write8(Global.$A, ALU.ADD(Registers.read8(Global.$X), Memory.read(value8)));
	checkArithmeticFlags(Registers.read8(Global.$A));}
	//0xB1
	public static void LDA_IDY(int value8){Registers.write8(Global.$A, Memory.read(ALU.ADD(Registers.read8(Global.$Y), value8)));
	checkArithmeticFlags(Registers.read8(Global.$A));}

/* ---------------------- LDX ---------------------- *
* @brief Load Index X with Memory
* Operation:   M -> X
* Flags Set:	N	Z	C	I	D	V
* 				+	+	-	-	-	-
*/
	//0xA2
	public static void LDX_IMM(int value8){Registers.write8(Global.$X, value8);
	checkArithmeticFlags(Registers.read8(Global.$X));}
	//0xA6
	public static void LDX_ZP(int value8){Registers.write8(Global.$X, Memory.read(value8));
	checkArithmeticFlags(Registers.read8(Global.$X));}
	//0xB6
	public static void LDX_ZPY(int value8){Registers.write8(Global.$X, Memory.read(ALU.ADD(value8, Registers.read8(Global.$Y))));
	checkArithmeticFlags(Registers.read8(Global.$X));}
	//0xAE
	public static void LDX_AB(int value16){Registers.write8(Global.$X, Memory.read(value16));
	checkArithmeticFlags(Registers.read8(Global.$X));}
	//0xBE
	public static void LDX_ABY(int value16){Registers.write8(Global.$X, Memory.read( ALU.ADD(value16,Registers.read8(Global.$Y))));
	checkArithmeticFlags(Registers.read8(Global.$X));}

/* ---------------------- LDY ---------------------- *
* @brief Load Index Y with Memory
* Operation:   M -> Y
* Flags Set:	N	Z	C	I	D	V
* 				+	+	-	-	-	-
*/
	//0xA0
	public static void LDY_IMM(int value8){Registers.write8(Global.$Y, value8);
	checkArithmeticFlags(Registers.read8(Global.$Y));}
	//0xA4
	public static void LDY_ZP(int value8){Registers.write8(Global.$Y, Memory.read(value8));
	checkArithmeticFlags(Registers.read8(Global.$Y));}
	//0xB4
	public static void LDY_ZPX(int value8){Registers.write8(Global.$Y, Memory.read(ALU.ADD(value8, Registers.read8(Global.$X))));
	checkArithmeticFlags(Registers.read8(Global.$Y));}
	//0xAC
	public static void LDY_AB(int value16){Registers.write8(Global.$Y, Memory.read(value16));
	checkArithmeticFlags(Registers.read8(Global.$Y));}
	//0xBC
	public static void LDY_ABX(int value16){Registers.write8(Global.$Y, Memory.read( ALU.ADD(value16,Registers.read8(Global.$X))));
	checkArithmeticFlags(Registers.read8(Global.$Y));}

/* ---------------------- STA ---------------------- *
* @brief Store Accumulator in Memory
* Operation:   A -> M
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	-	-
*/
	//0x85
	public static void STA_ZP(int value8){Memory.write(value8, (Registers.read8(Global.$A)));}
	//0x95
	public static void STA_ZPX(int value8){Memory.write(ALU.ADD( value8, Registers.read8(Global.$X)), Registers.read8(Global.$A) );}
	//0x8D
	public static void STA_AB(int value16){Memory.write(value16, Registers.read8(Global.$A));}
	//0x9D
	public static void STA_ABX(int value16){Memory.write(ALU.ADD( value16, Registers.read8(Global.$X)), Registers.read8(Global.$A) );}
	//0x99
	public static void STA_ABY(int value16){Memory.write(ALU.ADD( value16, Registers.read8(Global.$Y)), Registers.read8(Global.$A) );}
	//0x81
	public static void STA_IDX(int value8){Memory.write(ALU.ADD(Registers.read8(Global.$X), Memory.read(value8)), Registers.read8(Global.$A));}
	//0x91
	public static void STA_IDY(int value8){Memory.write(Memory.read(ALU.ADD(Registers.read8(Global.$Y), value8)), Registers.read8(Global.$A));}

/* ---------------------- STX ---------------------- *
* @brief Store Index X in Memory
* Operation:   X -> M
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	-	-
*/
	//0x86
	public static void STX_ZP(int value8){Memory.write(value8, Registers.read8(Global.$X));}
	//0x96
	public static void STX_ZPY(int value8){Memory.write(ALU.ADD( value8, Registers.read8(Global.$Y)), Registers.read8(Global.$X) );}
	//0x8E
	public static void STX_AB(int value16){Memory.write(value16, Registers.read8(Global.$X));}

/* ---------------------- STY ---------------------- *
* @brief Store Index Y in Memory
* Operation:   Y -> M
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	-	-
*/
	//0x84
	public static void STY_ZP(int value8){Memory.write(value8, Registers.read8(Global.$Y));}
	//0x94
	public static void STY_ZPX(int value8){Memory.write(ALU.ADD( value8, Registers.read8(Global.$X)), Registers.read8(Global.$Y) );}
	//0x8C
	public static void STY_AB(int value16){Memory.write(value16, Registers.read8(Global.$Y));}

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
    public static void TAX_IMP(){
        Registers.write8(Global.$X, Registers.read8(Global.$A));
        checkArithmeticFlags(Registers.read8(Global.$X));
    }

/* ---------------------- TAY ---------------------- *
* @brief Transfer Accumulator to Index Y
* Operation:   A -> Y
* Flags Set:	N	Z	C	I	D	V
* 				+	+	-	-	-	-
*/
    //0xA8
    public static void TAY_IMP(){
        Registers.write8(Global.$Y, Registers.read8(Global.$A));
        checkArithmeticFlags(Registers.read8(Global.$Y));
    }

/* ---------------------- TXA ---------------------- *
* @brief Transfer Index X to Accumulator
* Operation:   X -> A
* Flags Set:	N	Z	C	I	D	V
* 				+	+	-	-	-	-
*/
    //0x8A
    public static void TXA_IMP(){
        Registers.write8(Global.$A, Registers.read8(Global.$X));
        checkArithmeticFlags(Registers.read8(Global.$A));
    }

/* ---------------------- TYA ---------------------- *
* @brief Transfer Index Y to Accumulator
* Operation:   Y -> A
* Flags Set:	N	Z	C	I	D	V
* 				+	+	-	-	-	-
*/
    //0x98
    public static void TYA_IMP(){
        Registers.write8(Global.$A, Registers.read8(Global.$Y));
        checkArithmeticFlags(Registers.read8(Global.$A));
    }

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
    public static void TSX_IMP(){Registers.write8(Global.$X, Registers.readSP());checkArithmeticFlags(Registers.read8(Global.$X));}

/* ---------------------- TXS ---------------------- *
* @brief Transfer Index X to Stack Register
* Operation:  x -> SP
* Flags Set:	N	Z	C	I	D	V
* 				+	+	-	-	-	-
*/
    //0x9A
    public static void TXS_IMP(){Registers.writeSP(Registers.read8(Global.$X));checkArithmeticFlags(Registers.read8(Global.$A));}

/* ---------------------- PHA ---------------------- *
* @brief Push Accumulator on Stack
* Operation:  push A
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	-	-
*/
    //48
    public static void PHA_IMP(){helperStackPush(Registers.read8(Global.$A));}

/* ---------------------- PHP ---------------------- *
* @brief Push Processor Status on Stack, and set break flag inside the stack only.
* Operation:  push SR, and set break flag inside stack only.
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	-	-
*/
    //0x08
    public static void PHP_IMP(){helperStackPush(Registers.read8(Global.$P) | 0x10);}

/* ---------------------- PLA ---------------------- *
* @brief Pull Accumulator from Stack
* Operation:  pull A
* Flags Set:	N	Z	C	I	D	V
* 				+	+	-	-	-	-
*/
    //0x68
    public static void PLA_IMP(){Registers.write8(Global.$A, helperStackPop());checkArithmeticFlags(Registers.read8(Global.$A));}

/* ---------------------- PLP ---------------------- *
* @brief Pull Processor Status from Stack
* Operation:  pull SR
* Flags Set:	N	Z	C	I	D	V
* 				flag results from stack
*/
    //0x28
    public static void PLP_IMP(){Registers.write8(Global.$P, helperStackPop());}

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
    public static void AND_IMM(int value8){
        Registers.write8(Global.$A, ALU.AND(Registers.read8(Global.$A), value8));
        checkArithmeticFlags(Registers.read8(Global.$A));
    }
    //0x25
    public static void AND_ZP(int value8){
        Registers.write8(Global.$A, ALU.AND(Registers.read8(Global.$A), Memory.read(value8)));
        checkArithmeticFlags(Registers.read8(Global.$A));
    }
    //0x35
    public static void AND_ZPX(int value8){
        Registers.write8(Global.$A, ALU.AND(Registers.read8(Global.$A), Memory.read(ALU.ADD(value8, Registers.read8(Global.$X)))));
        checkArithmeticFlags(Registers.read8(Global.$A));
    }
    //0x2D
    public static void AND_AB(int value16){
        Registers.write8(Global.$A, ALU.AND(Registers.read8(Global.$A), Memory.read(value16)));
        checkArithmeticFlags(Registers.read8(Global.$A));
    }
    //0x3D
    public static void AND_ABX(int value16){
        Registers.write8(Global.$A, ALU.AND(Registers.read8(Global.$A), Memory.read(ALU.ADD(value16, Registers.read8(Global.$X)))));
        checkArithmeticFlags(Registers.read8(Global.$A));
    }
    //0x39
    public static void AND_ABY(int value16){
        Registers.write8(Global.$A, ALU.AND(Registers.read8(Global.$A), Memory.read(ALU.ADD(value16, Registers.read8(Global.$Y)))));
        checkArithmeticFlags(Registers.read8(Global.$A));
    }
    //0x21
    public static void AND_IDX(int value8){
        Registers.write8(Global.$A, ALU.AND(Registers.read8(Global.$A), Memory.read(ALU.ADD(Memory.read(value8), Registers.read8(Global.$X)))));
        checkArithmeticFlags(Registers.read8(Global.$A));
    }
    //0x31
    public static void AND_IDY(int value8){
        Registers.write8(Global.$A, ALU.AND(Registers.read8(Global.$A), Memory.read(ALU.ADD(Memory.read(value8), Registers.read8(Global.$Y)))));
        checkArithmeticFlags(Registers.read8(Global.$A));
    }

/* ---------------------- EOR ---------------------- *
* @brief Exclusive-OR Memory with Accumulator
* Operation:  A EOR M -> A
* Flags Set:	N	Z	C	I	D	V
* 				+	+	-	-	-	-
*/
    //0x49
    public static void EOR_IMM(int value8){
        Registers.write8(Global.$A, ALU.XOR(Registers.read8(Global.$A), value8));
        checkArithmeticFlags(Registers.read8(Global.$A));
    }
    //0x45
    public static void EOR_ZP(int value8){
        Registers.write8(Global.$A, ALU.XOR(Registers.read8(Global.$A), Memory.read(value8)));
        checkArithmeticFlags(Registers.read8(Global.$A));
    }
    //0x55
    public static void EOR_ZPX(int value8){
        Registers.write8(Global.$A, ALU.XOR(Registers.read8(Global.$A), Memory.read(ALU.ADD(value8, Registers.read8(Global.$X)))));
        checkArithmeticFlags(Registers.read8(Global.$A));
    }
    //0x4D
    public static void EOR_AB(int value16){
        Registers.write8(Global.$A, ALU.XOR(Registers.read8(Global.$A),  Memory.read(value16)));
        checkArithmeticFlags(Registers.read8(Global.$A));
    }
    //0x5D
    public static void EOR_ABX(int value16){
        Registers.write8(Global.$A, ALU.XOR(Registers.read8(Global.$A), Memory.read(ALU.ADD(value16, Registers.read8(Global.$X)))));
        checkArithmeticFlags(Registers.read8(Global.$A));
    }
    //0x59
    public static void EOR_ABY(int value16){
        Registers.write8(Global.$A, ALU.XOR(Registers.read8(Global.$A), Memory.read(ALU.ADD(value16, Registers.read8(Global.$Y)))));
        checkArithmeticFlags(Registers.read8(Global.$A));
    }
    //0x41
    public static void EOR_IDX(int value8){
        Registers.write8(Global.$A, ALU.XOR(Registers.read8(Global.$A), Memory.read(ALU.ADD( Memory.read(value8), Registers.read8(Global.$X)))));
        checkArithmeticFlags(Registers.read8(Global.$A));
    }
    //0x51
    public static void EOR_IDY(int value8){
        Registers.write8(Global.$A, ALU.XOR(Registers.read8(Global.$A), Memory.read(ALU.ADD( Memory.read(value8), Registers.read8(Global.$Y)))));
        checkArithmeticFlags(Registers.read8(Global.$A));}

/* ---------------------- ORA ---------------------- *
* @brief OR Memory with Accumulator
* Operation:  A OR M -> A
* Flags Set:	N	Z	C	I	D	V
* 				+	+	-	-	-	-
*/
    //0x09
    public static void ORA_IMM(int value8){
        Registers.write8(Global.$A, ALU.OR(Registers.read8(Global.$A), value8));
        checkArithmeticFlags(Registers.read8(Global.$A));
    }
    //0x05
    public static void ORA_ZP(int value8){
        Registers.write8(Global.$A, ALU.OR(Registers.read8(Global.$A), Memory.read(value8)));
        checkArithmeticFlags(Registers.read8(Global.$A));
    }
    //0x15
    public static void ORA_ZPX(int value8){
        Registers.write8(Global.$A, ALU.OR(Registers.read8(Global.$A), Memory.read(ALU.ADD(value8, Registers.read8(Global.$X)))));
        checkArithmeticFlags(Registers.read8(Global.$A));
    }
    //0x0D
    public static void ORA_AB(int value16){
        Registers.write8(Global.$A, ALU.OR(Registers.read8(Global.$A),  Memory.read(value16)));
        checkArithmeticFlags(Registers.read8(Global.$A));
    }
    //0x1D
    public static void ORA_ABX(int value16){
        Registers.write8(Global.$A, ALU.OR(Registers.read8(Global.$A), Memory.read(ALU.ADD(value16, Registers.read8(Global.$X)))));
        checkArithmeticFlags(Registers.read8(Global.$A));
    }
    //0x19
    public static void ORA_ABY(int value16){
        Registers.write8(Global.$A, ALU.OR(Registers.read8(Global.$A), Memory.read(ALU.ADD(value16, Registers.read8(Global.$Y)))));
        checkArithmeticFlags(Registers.read8(Global.$A));
    }
    //0x01
    public static void ORA_IDX(int value8){
        Registers.write8(Global.$A, ALU.OR(Registers.read8(Global.$A), Memory.read(ALU.ADD(Memory.read(value8), Registers.read8(Global.$X)))));
        checkArithmeticFlags(Registers.read8(Global.$A));}
    //0x11
    public static void ORA_IDY(int value8){
        Registers.write8(Global.$A, ALU.OR(Registers.read8(Global.$A), Memory.read(ALU.ADD( Memory.read(value8), Registers.read8(Global.$Y)))));
        checkArithmeticFlags(Registers.read8(Global.$A));}

/* ---------------------- BIT ---------------------- *
* @brief Test Bits in Memory with Accumulator bits 7 and 6 of operand are transferred to bit 7 and 6 of SR (N,V);
* the zeroflag is set to the result of operand AND accumulator.
* Operation:  A AND M, M7 -> N, M6 -> V
* Flags Set:	N	Z	C	I	D	V
* 				M7	+	-	-	-	M6
*/
    //0x24
    public static void BIT_ZP(int value8){
    	int temp = ALU.AND(Registers.read8(Global.$A), Memory.read(value8));
		if (Registers.read8(temp)== 0){
			Registers.setZero();
		}
        if (getBit(Registers.read8(temp), 6) != getBit(Registers.read8(Global.$P), 6)){
            Registers.write8(Global.$P, Registers.read8(Global.$P) ^ (1 << 6)); //flip the bit to match temp bit
        }
        if (getBit(Registers.read8(temp), 7) != getBit(Registers.read8(Global.$P), 7)){
            Registers.write8(Global.$P, Registers.read8(Global.$P) ^ (1 << 7)); //flip the bit to match temp bit
        }
    }
    //0x2C
    public static void BIT_AB(int value16){
    	int temp = ALU.AND(Registers.read8(Global.$A), Memory.read(value16));
		if (Registers.read16(temp) == 0){
			Registers.setZero();
		}
        if (getBit(Registers.read16(temp), 6) != getBit(Registers.read8(Global.$P), 6)){
            Registers.write8(Global.$P, Registers.read8(Global.$P) ^ (1 << 6)); //flip the bit to match temp bit
        }
        if (getBit(Registers.read16(temp), 7) != getBit(Registers.read8(Global.$P), 7)){
            Registers.write8(Global.$P, Registers.read8(Global.$P) ^ (1 << 7)); //flip the bit to match temp bit
        }
    }

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
	public static void ADC_IMM(int value8){     Registers.write8(Global.$A, ALU.ADD(Registers.read8(Global.$A), value8));}
    //0x65
	public static void ADC_ZP(int value8){      Registers.write8(Global.$A, ALU.ADD(Registers.read8(Global.$A), Memory.read(value8)));}
    //0x75
	public static void ADC_ZPX(int value8){     Registers.write8(Global.$A, ALU.ADD(Registers.read8(Global.$A), Memory.read(ALU.ADD(value8, Registers.read8(Global.$X)))));}
	//0x6D
	public static void ADC_AB(int value16){     Registers.write8(Global.$A, ALU.ADD(Registers.read8(Global.$A), Memory.read(value16)));}
    //0x7D
	public static void ADC_ABX(int value16){    Registers.write8(Global.$A, ALU.ADD(Registers.read8(Global.$A), Memory.read(ALU.ADD(value16, Registers.read8(Global.$X)))));}
    //0x79
	public static void ADC_ABY(int value16){    Registers.write8(Global.$A, ALU.ADD(Registers.read8(Global.$A), Memory.read(ALU.ADD(value16, Registers.read8(Global.$Y)))));}
    //0x61
	public static void ADC_IDX(int value8){     Registers.write8(Global.$A, ALU.ADD(Registers.read8(Global.$A), ALU.ADD(Memory.read(value8), Registers.read8(Global.$X))));}
    //0x71
	public static void ADC_IDY(int value8){     Registers.write8(Global.$A, ALU.ADD(Registers.read8(Global.$A), ALU.ADD(Memory.read(value8), Registers.read8(Global.$Y))));}

/* ---------------------- SBC ---------------------- *
* @brief Subtract Memory from Accumulator with Borrow
* Operation:   A - M - C -> A
* Flags Set:	N	Z	C	I	D	V
* 				+	+	+	-	-	+
*/
    //0xE9
    public static void SBC_IMM(int value8){     Registers.write8(Global.$A, helperSBC(Registers.read8(Global.$A), value8)); checkArithmeticFlags(Registers.read8(Global.$A));}
    //0xE5
    public static void SBC_ZP(int value8){      Registers.write8(Global.$A, helperSBC(Registers.read8(Global.$A), Memory.read(value8)));checkArithmeticFlags(Registers.read8(Global.$A));}
    //0xF5
    public static void SBC_ZPX(int value8){     Registers.write8(Global.$A, helperSBC(Registers.read8(Global.$A), Memory.read(ALU.ADD(value8, Registers.read8(Global.$X)))));checkArithmeticFlags(Registers.read8(Global.$A));}
    //0xED
    public static void SBC_AB(int value16){     Registers.write8(Global.$A, helperSBC(Registers.read8(Global.$A), Memory.read(value16)));checkArithmeticFlags(Registers.read8(Global.$A));}
    //0xFD
    public static void SBC_ABX(int value16){    Registers.write8(Global.$A, helperSBC(Registers.read8(Global.$A), Memory.read(ALU.ADD(value16, Registers.read8(Global.$X)))));checkArithmeticFlags(Registers.read8(Global.$A));}
    //0xF9
    public static void SBC_ABY(int value16){    Registers.write8(Global.$A, helperSBC(Registers.read8(Global.$A), Memory.read(ALU.ADD(value16, Registers.read8(Global.$Y)))));checkArithmeticFlags(Registers.read8(Global.$A));}
    //0xE1
    public static void SBC_IDX(int value8){     Registers.write8(Global.$A, helperSBC(Registers.read8(Global.$A), ALU.ADD(Memory.read(value8), Registers.read8(Global.$X))));checkArithmeticFlags(Registers.read8(Global.$A));}
    //0xF1
    public static void SBC_IDY(int value8){     Registers.write8(Global.$A, helperSBC(Registers.read8(Global.$A), ALU.ADD(Memory.read(value8), Registers.read8(Global.$Y))));checkArithmeticFlags(Registers.read8(Global.$A));}

/* ---------------------- CMP ---------------------- *
* @brief Compare Memory with Accumulator
* Operation:   A - M
* Flags Set:	N	Z	C	I	D	V
* 				+	+	+	-	-	-
*/
    //0xC9
    public static void CMP_IMM(int value8){     helperCMP(Registers.read8(Global.$A), value8);checkArithmeticFlags(Registers.read8(Global.$A));}
    //0xC5
    public static void CMP_ZP(int value8){      helperCMP(Registers.read8(Global.$A), Memory.read(value8));checkArithmeticFlags(Registers.read8(Global.$A));}
    //0xD5
    public static void CMP_ZPX(int value8){     helperCMP(Registers.read8(Global.$A), Memory.read(ALU.ADD(Registers.read8(Global.$X), value8)));checkArithmeticFlags(Registers.read8(Global.$A));}
    //0xCD
    public static void CMP_AB(int value16){     helperCMP(Registers.read8(Global.$A), Memory.read(value16));checkArithmeticFlags(Registers.read8(Global.$A));}
    //0xDD
    public static void CMP_ABX(int value16){    helperCMP(Registers.read8(Global.$A), Memory.read(ALU.ADD(Registers.read8(Global.$X), value16)));checkArithmeticFlags(Registers.read8(Global.$A));}
    //0xD9
    public static void CMP_ABY(int value16){    helperCMP(Registers.read8(Global.$A), Memory.read(ALU.ADD(Registers.read8(Global.$Y), value16)));checkArithmeticFlags(Registers.read8(Global.$A));}
    //0xC1
    public static void CMP_IDX(int value8){     helperCMP(Registers.read8(Global.$A), ALU.ADD(Registers.read8(Global.$X), Memory.read(value8)));checkArithmeticFlags(Registers.read8(Global.$A));}
    //0xD1
    public static void CMP_IDY(int value8){     helperCMP(Registers.read8(Global.$A), Memory.read(ALU.ADD(Registers.read8(Global.$Y), value8)));checkArithmeticFlags(Registers.read8(Global.$A));}

/* ---------------------- CPX ---------------------- *
* @brief Compare Memory and Index X
* Operation:   X - M
* Flags Set:	N	Z	C	I	D	V
* 				+	+	+	-	-	-
*/
    //0xE0
    public static void CPX_IMM(int value8){     helperCMP(Registers.read8(Global.$X), value8);checkArithmeticFlags(Registers.read8(Global.$X));}
    //0xE4
    public static void CPX_ZP(int value8){      helperCMP(Registers.read8(Global.$X), Memory.read(value8));checkArithmeticFlags(Registers.read8(Global.$X));}
    //0xEC
    public static void CPX_AB(int value16){     helperCMP(Registers.read8(Global.$X), Memory.read(value16));checkArithmeticFlags(Registers.read8(Global.$X));}

/* ---------------------- CPY ---------------------- *
* @brief Compare Memory and Index Y
* Operation:   Y - M
* Flags Set:	N	Z	C	I	D	V
* 				+	+	+	-	-	-
*/
    //0xC0
    public static void CPY_IMM(int value8){     helperCMP(Registers.read8(Global.$Y), value8);checkArithmeticFlags(Registers.read8(Global.$Y));}
    //0xC4
    public static void CPY_ZP(int value8){      helperCMP(Registers.read8(Global.$Y), Memory.read(value8));checkArithmeticFlags(Registers.read8(Global.$Y));}
    //0xCC
    public static void CPY_AB(int value16){     helperCMP(Registers.read8(Global.$Y), Memory.read(value16));checkArithmeticFlags(Registers.read8(Global.$Y));}


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
	public static void INC_ZP(int value8){Memory.write(value8, ALU.ADD(Memory.read(value8), 1));checkArithmeticFlags(Memory.read(value8));}
	//0xF6
	public static void INC_ZPX(int value8){int temp = ALU.ADD(value8, Registers.read8(Global.$X));Memory.write(temp, ALU.ADD(Memory.read(temp), 1));checkArithmeticFlags(Memory.read(temp));}
	//0xEE
	public static void INC_AB(int value16){Memory.write(value16, ALU.ADD(Memory.read(value16), 1));checkArithmeticFlags(Memory.read(value16));}
	//0xFE
	public static void INC_ABX(int value16){int temp = ALU.ADD(value16, Registers.read8(Global.$X));Memory.write(temp, ALU.ADD(Memory.read(temp), 1));checkArithmeticFlags(Memory.read(temp));}

/* ---------------------- INX ---------------------- *
* @brief Increment Index X by One
* Operation:   X + 1 -> X
* Flags Set:	N	Z	C	I	D	V
* 				+	+	-	-	-	-
*/
	//0xE8
	public static void INX_IMP(){Registers.write8(Global.$X, ALU.ADD(Registers.read8(Global.$X), 1));checkArithmeticFlags(Registers.read8(Global.$X));}

/* ---------------------- INY ---------------------- *
* @brief Increment Index Y by One
* Operation:   Y + 1 -> Y
* Flags Set:	N	Z	C	I	D	V
* 				+	+	-	-	-	-
*/
	//0xC8
	public static void INY_IMP(){Registers.write8(Global.$Y, ALU.ADD(Registers.read8(Global.$Y), 1));checkArithmeticFlags(Registers.read8(Global.$Y));}

/* ---------------------- DEC ---------------------- *
* @brief Decrement Memory by One
* Operation:   M - 1 -> M
* Flags Set:	N	Z	C	I	D	V
* 				+	+	-	-	-	-
*/
	//0xC6
	public static void DEC_ZP(int value8){Memory.write(value8, ALU.ADD(Memory.read(value8), -1));checkArithmeticFlags(Memory.read(value8));}
	//0xD6
	public static void DEC_ZPX(int value8){int temp = ALU.ADD(value8, Registers.read8(Global.$X));Memory.write(temp, ALU.ADD(Memory.read(temp), -1));checkArithmeticFlags(Memory.read(temp));}
	//0xCE
	public static void DEC_AB(int value16){Memory.write(value16, ALU.ADD(Memory.read(value16), -1));checkArithmeticFlags(Memory.read(value16));}
	//0xDE
	public static void DEC_ABX(int value16){int temp = ALU.ADD(value16, Registers.read8(Global.$X));Memory.write(temp, ALU.ADD(Memory.read(temp), -1));checkArithmeticFlags(Memory.read(temp));}

/* ---------------------- DEX ---------------------- *
* @brief Decrement Index X by One
* Operation:   X - 1 -> X
* Flags Set:	N	Z	C	I	D	V
* 				+	+	-	-	-	-
*/
	//0xCA
	public static void DEX_IMP(){Registers.write8(Global.$X, ALU.ADD(Registers.read8(Global.$X), -1));checkArithmeticFlags(Registers.read8(Global.$X));}

/* ---------------------- DEY ---------------------- *
* @brief Decrement Index Y by One
* Operation:   Y - 1 -> Y
* Flags Set:	N	Z	C	I	D	V
* 				+	+	-	-	-	-
*/
	//0x88
	public static void DEY_IMP(){Registers.write8(Global.$Y, ALU.ADD(Registers.read8(Global.$Y), -1));checkArithmeticFlags(Registers.read8(Global.$Y));}

/* ====================== SHIFT OPERATIONS =========================== 
*Shift instructions cause the bits within either a memory location or the 
*accumulator to be shifted by one bit position. The rotate instructions 
*use the contents if the carry flag (C) to fill the vacant position 
*generated by the shift and to catch the overflowing bit. The arithmetic 
*and logical shifts shift in an appropriate 0 or 1 bit as appropriate but 
*catch the overflow bit in the carry flag (C).
*/
//todo these are all pretty confusing
/* ---------------------- ASL ---------------------- *
* @brief Shift Left One Bit (Memory or Accumulator)
* Operation:   C <- [76543210] <- 0 
* Flags Set:	N	Z	C	I	D	V
* 				+	+	+	-	-	-
*/
	//0x0A
	public static void ASL_ACC(){Registers.write8(Global.$A, helperASL(Registers.read8(Global.$A)));}
	//0x06
	public static void ASL_ZP(int value8){Memory.write(value8, helperASL(Memory.read(value8)));}
	//0x16
	public static void ASL_ZPX(int value8){Memory.write(ALU.ADD(value8, Registers.read8(Global.$X)), helperASL(Memory.read(Memory.read(ALU.ADD(value8,Registers.read8(Global.$X))))));}
	//0x0E
	public static void ASL_AB(int value16){Memory.write(value16, helperASL(Memory.read(value16)));}
	//0x1E
	public static void ASL_ABX(int value16){Memory.write(ALU.ADD(value16, Registers.read8(Global.$X)), helperASL(Memory.read(Memory.read(ALU.ADD(value16, Registers.read8(Global.$X))))));}

/* ---------------------- LSR ---------------------- *
* @brief Shift One Bit Right (Memory or Accumulator)
* Operation:   0 -> [76543210] -> C
* Flags Set:	N	Z	C	I	D	V
* 				-	+	+	-	-	-
*/
	//0x4A
	public static void LSR_ACC(){Registers.write8(Global.$A, helperLSR(Registers.read8(Global.$A)));}
	//0x46
	public static void LSR_ZP(int value8){Memory.write(value8, helperLSR(Memory.read(value8)));}
	//0x56
	public static void LSR_ZPX(int value8){Memory.write(ALU.ADD(value8, Registers.read8(Global.$X)), helperLSR(Memory.read(Memory.read(ALU.ADD(value8,Registers.read8(Global.$X))))));}
	//0x4E
	public static void LSR_AB(int value16){Memory.write(value16, helperLSR(Memory.read(value16)));}
	//0x5E
	public static void LSR_ABX(int value16){Memory.write(ALU.ADD(value16, Registers.read8(Global.$X)), helperLSR(Memory.read(Memory.read(ALU.ADD(value16, Registers.read8(Global.$X))))));}

/* ---------------------- ROL ---------------------- *
* @brief Rotate One Bit Left (Memory or Accumulator)
* Operation:   C <- [76543210] <- C 
* Flags Set:	N	Z	C	I	D	V
* 				+	+	+	-	-	-
*/
	//0x2A
	public static void ROL_ACC(){Registers.write8(Global.$A, helperROL(Registers.read8(Global.$A)));}
	//0x26
	public static void ROL_ZP(int value8){Memory.write(value8, helperROL(Memory.read(value8)));}
	//0x36
	public static void ROL_ZPX(int value8){Memory.write(ALU.ADD(value8, Registers.read8(Global.$X)), helperROL(Memory.read(Memory.read(ALU.ADD(value8,Registers.read8(Global.$X))))));}
	//0x2E
	public static void ROL_AB(int value16){Memory.write(value16, helperROL(Memory.read(value16)));}
	//0x3E
	public static void ROL_ABX(int value16){Memory.write(ALU.ADD(value16, Registers.read8(Global.$X)), helperROL(Memory.read(Memory.read(ALU.ADD(value16, Registers.read8(Global.$X))))));}

/* ---------------------- ROR ---------------------- *
* @brief Rotate One Bit Right (Memory or Accumulator)
* Operation:   C -> [76543210] -> C 
* Flags Set:	N	Z	C	I	D	V
* 				+	+	+	-	-	-
*/
	//0x6A
	public static void ROR_ACC(){Registers.write8(Global.$A, helperROR(Registers.read8(Global.$A)));}
	//0x66
	public static void ROR_ZP(int value8){Memory.write(value8, helperROR(Memory.read(value8)));}
	//0x76
	public static void ROR_ZPX(int value8){Memory.write(ALU.ADD(value8, Registers.read8(Global.$X)), helperROR(Memory.read(Memory.read(ALU.ADD(value8,Registers.read8(Global.$X))))));}
	//0x6E
	public static void ROR_AB(int value16){Memory.write(value16, helperROR(Memory.read(value16)));}
	//0x7E
	public static void ROR_ABX(int value16){Memory.write(ALU.ADD(value16, Registers.read8(Global.$X)), helperROR(Memory.read(Memory.read(ALU.ADD(value16, Registers.read8(Global.$X))))));}


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
    public static void JMP_AB(int value16){Registers.writePC(value16 + 0x200);}
    //0x6C
    public static void JMP_ID(int value16){Registers.writePC(Memory.read(value16));} //TODO CHECK

/* ---------------------- JSR ---------------------- *
* @brief Jump to New Location Saving Return Address
* Operation:   (push (PC+2), then either
* Operation:   (PC+1) -> PCL
* Operation:   (PC+2) -> PCH
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	-	-
*/

    //0x20
    public static void JSR_AB(int value16){
    	helperStackPush(Registers.read8(Global.$P));
    	int tmp = Registers.readPC() - 3;
    	helperStackPush(tmp << 2); //PC is incremented before this function is called
		helperStackPush(tmp >> 2); //PC is incremented before this function is called
		Registers.writePC(value16);
	}

/* ---------------------- RTS ---------------------- *
* @brief Return from Subroutine
* Operation:   pull PC, then
* Operation:   PC+1 -> PC
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	-	-
*/

    //0x60
    public static void RTS_IMP(){
    	int tmpLow = helperStackPop();
    	int tmpHigh= helperStackPop();
    	Registers.writePC(CPU.littleEndian(tmpLow, tmpHigh) + 1);

	}

/* ====================== BRANCHES OPERATIONS ======================== 
*Branch instructions break the normal sequential flow of execution by changing
*the program counter if a specified condition is met. All the conditions 
*are based on examining a single bit within the processor status. ALl numbers are signed.
*/

/* ---------------------- BCC ---------------------- *
* @brief Branch on Carry Clear
* Operation:   branch on C = 0 
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	-	-
*/
	//0x90
	public static void BCC_REL(int value8){if(!Registers.isCarry()){Registers.writePC(value8 + 0x200);}}

/* ---------------------- BCS ---------------------- *
* @brief Branch on Carry Set
* Operation:   branch on C = 1
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	-	-
*/
	//0xB0
	public static void BCS_REL(int value8){if(Registers.isCarry()){Registers.writePC(value8 + 0x200);}}

/* ---------------------- BEQ ---------------------- *
* @brief Branch on Result Zero
* Operation:   branch on Z = 1
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	-	-
*/
	//0xF0
	public static void BEQ_REL(int value8){if(Registers.isZero()){Registers.writePC(value8 + 0x200);}}

/* ---------------------- BMI ---------------------- *
* @brief Branch on Result Minus
* Operation:   branch on N = 1
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	-	-
*/
	//0x30
	public static void BMI_REL(int value8){if(Registers.isNegative()){Registers.writePC(value8 + 0x200);}}

/* ---------------------- BNE ---------------------- *
* @brief Branch on Result not Zero
* Operation:   branch on Z != 0
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	-	-
*/
	//0xD0
	public static void BNE_REL(int value8){if(!Registers.isZero()){Registers.writePC(value8+ 0x200);}}

/* ---------------------- BPL ---------------------- *
* @brief Branch on Result not Zero
* Operation:   branch on N = 0
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	-	-
*/
	//0x10
	public static void BPL_REL(int value8){if(!Registers.isNegative()){Registers.writePC(value8 + 0x200);}}

/* ---------------------- BVC ---------------------- *
* @brief Branch on Overflow Clear
* Operation:   branch on V = 0
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	-	-
*/
	//0x50
	public static void BVC_REL(int value8){if(!Registers.isOverflow()){Registers.writePC(value8 + 0x200);}}

/* ---------------------- BVS ---------------------- *
* @brief Branch on Overflow Set
* Operation:   branch on V = 1
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	-	-
*/
	//0x50
	public static void BVS_REL(int value8){if(Registers.isOverflow()){Registers.writePC(value8 + 0x200);}}

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
    public static void CLC_IMP(){Registers.resetCarry();}

/* ---------------------- CLD ---------------------- *
* @brief Clear Decimal Mode
* Operation:   0 -> C
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	0	-
*/
    //0xD8
    public static void CLD_IMP(){Registers.resetDecimal();}

/* ---------------------- CLI ---------------------- *
* @brief Clear Interrupt Disable Bit
* Operation:   0 -> I
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	0	-	-
*/
    //0x58
    public static void CLI_IMP(){Registers.resetIRQDisabled();}

/* ---------------------- CLV ---------------------- *
* @brief Clear Overflow Flag
* Operation:   0 -> V
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	-	0
*/
    //0xB8
    public static void CLV_IMP(){Registers.resetOverflow();}

/* ---------------------- SEC ---------------------- *
* @brief Set Carry Flag
* Operation:   1 -> C
* Flags Set:	N	Z	C	I	D	V
* 				-	-	1	-	-	-
*/
    //0x38
    public static void SEC_IMP(){Registers.setCarry();}

/* ---------------------- SED ---------------------- *
* @brief Set Decimal Flag
* Operation:   1 -> D
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	1	-
*/
    //0xF8
    public static void SED_IMP(){Registers.setDecimal();}

/* ---------------------- SEI ---------------------- *
* @brief Set Interrupt Disable Status
* Operation:   1 -> I
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	1	-	-
*/
    //0x78
    public static void SEI_IMP(){Registers.setIRQDisabled();}

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
    public static void BRK_IMP(){
    	Registers.setIRQDisabled(); Registers.setBreak();
    	Registers.incrPC(2);
    	}

/* ---------------------- NOP ---------------------- *
* @brief No-Operation
* Operation: None.
* Flags Set:	N	Z	C	I	D	V
* 				-	-	-	-	-	-
*/
    //0xEA
    public static void NOP_IMP(){return;}

/* ---------------------- RTI ---------------------- *
* @brief Return from interrupt
* Operation: pull SR, pull PC
* Flags Set:	N	Z	C	I	D	V
* 				info comes from stack
*/
    //0x40
    public static void RTI_IMP(){
    	Registers.writeSP(helperStackPop());
		int tmpLow = helperStackPop();
		int tmpHigh= helperStackPop();
		//Registers.writePC(CPU.littleEndian(tmpLow, tmpHigh) + 1); No writing to the PC
    	}




/* HELPER FUNCTIONS */

    private static int getBit(int x, int y){
        return (x & 1 << y);
    }

	private static boolean checkBit(int x, int y) {
        return (x & 1 << y) != 0;
	}

	private static void checkArithmeticFlags(int value){

        //you have to pass this function the VALUE, not the position in memorys
    	if (value == 0){
    		Registers.setZero();
    	} else {
    		Registers.resetZero();
		}
    	if (checkBit(value, 7)){
    		Registers.setNegative();
    	} else {
    		Registers.resetNegative();
		}
	}

	private static int helperSBC(int value1, int value2){
        int tmp =  value1 - value2 - (Registers.isCarry() ? 0 : 1);
        if(tmp < -128 || tmp > 127){
            Registers.setOverflow();
            tmp &= 0x80;
        }
        checkArithmeticFlags(tmp);
        return tmp & 0xFF;
    }

    //todo implement
    private static void helperCMP(int value1, int value2){
        if(value1 == value2){
            Registers.setZero();
            Registers.setCarry();
        } else if(value1 > value2){
            Registers.setCarry();
        } else {
            Registers.resetCarry();
            Registers.resetZero();
        }
    }


    private static void helperStackPush(int value){
        Memory.write(0x100 + Registers.readSP(), value);

        if (Registers.readSP() == 0) {
            Registers.writeSP(0xFF);
        } else {
            Registers.writeSP(Registers.readSP()-1);
        }
    }


    private static int helperStackPop(){
        if (Registers.readSP() == 0xff) {
            Registers.writeSP(0x00);
        } else {
            Registers.writeSP(Registers.readSP()+1);
        }

        return Memory.read(0x100 + Registers.readSP());
    }

    private static int helperASL(int value){
        if((value & 0x80) != 0){
            Registers.setCarry();
        } else {
        	Registers.resetCarry();
		}
        return (value << 1) & 0xFF;
    }

    private static int helperLSR(int value){
        if((value & 0x01) != 0){
            Registers.setCarry();
        } else {
			Registers.resetCarry();
		}
        return (value & 0xFF) >>> 0x01;
    }

    private static int helperROL(int value){
        int tmp = ((value << 1) | (Registers.isCarry() ? 1 : 0) & 0xFF );
        if((value & 0x80) != 0){
            Registers.setCarry();
        } else {
			Registers.resetCarry();
		}
        return tmp;
    }

    private static int helperROR(int value){
        int tmp = ((value >>> 1) | (Registers.isCarry() ? 1 : 0) & 0xFF );
        if((value & 0x80) != 0){
            Registers.setCarry();
        } else {
			Registers.resetCarry();
		}
        return tmp;
    }


}