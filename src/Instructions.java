public class Instructions {

public boolean Instructions(){return true;}

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
*               Affects Flags S V Z C                
*               A + M + C -> A, C                    */

/*@brief 
* @opcode 0x69
* @param 
*/
public static int adc_imm(){
	return -1;
}

/* ====================== INCREMENTS/DECREMENTS OPERATIONS =========== */

/* ====================== SHIFT OPERATIONS =========================== */

/* ====================== JUMPS/CALLS OPERATIONS ===================== */

/* ====================== BRANCHES OPERATIONS ======================== */

/* ====================== STATUS/FLAG OPERATIONS ===================== */

/* ======================  SYSTEM OPERATIONS ========================== */

	
}