public class CPU{
	//For now, this is going to be the same as the $PC, later, when pipelined
	//This will be different.
	private int clock;
	
	public CPU(){
		clock = 0;
	}

    /* @brief Run the CPU based on user input actions. Called for every CPU cycle. This will call decode.
    *
    *@params None.
    *@return Void.
    */
    public static void Execute(){
        while (Memory.read(Registers.readPC()) != null){
            CPURun();
        }
    }
	/* @brief Run the CPU based on user input actions. Called for every CPU cycle. This will call decode.
	*
	*@params None.
	*@return Void.
	*/
	public static void CPURun(){
        decode();
	}

    /* @brief Taken two values, will concatenate by little endian method.
    *
    *@params value1 first index, will be second.
    *@params value2 second index, will be first.
    *@return Void.
    */
	private static int littleEndian(int value1, int value2){
	    String result = "" + value2 + value1;
	    return Integer.parseInt(result);
    }

    /*
    @brief Will read the current $PC, use the $PC to find the correct instruction in memory
    * then increment the current $PC by 4 (in case it does get overwritten) and then find the correct
    * corresponding instruction to execute. PC needs to increment over the array correctly, not everything in
    * memory is an instruction, some are values. Some instructions take different levels of memory.
    *@info Will only be called by CPURun().
    *@params None.
    *@return Void.
    */
    private static void decode(){
    //TODO need to write method to obtain index of instruction opcodes.
        int opCode = Memory.read(Registers.readPC()); //TODO Need to increment PC Correctly based on the instruction and whats to follow.
        int value8 = Memory.read(Registers.readPC()+1);  //TODO Create data bank to store what we need to increment the pc by based on instruction.
        int value16 = littleEndian(value8, Memory.read(Registers.readPC()+2));
        Registers.incrPC(Databank.getJumpCode(opCode));


        //PREFETCH MAXIMUM AMOUNT OF ARGUMENTS
        //increment clock
        //increment pc
        //run the decode below.

        //IMPORTANT: Commented out opcodes are opcodes not available to the standard 6502, we will not support them for now.
        switch(opCode) {
            case 0x00: Instructions.BRK_IMP(); break;
            case 0x01: Instructions.ORA_IDX(value8); break;
            //case 0x02:
            //case 0x03:
            //case 0x04:
            case 0x05: Instructions.ORA_ZP(value8);  break;
            case 0x06: Instructions.ASL_ZP(value8);  break;
            //case 0x07:
            case 0x08: Instructions.PHP_IMP();       break;
            case 0x09: Instructions.ORA_IMM(value8); break;
            case 0x0A: Instructions.ASL_ACC();       break;
            //case 0x0B:
            //case 0x0C:
            case 0x0D: Instructions.ORA_AB(value16); break;
            case 0x0E: Instructions.ASL_AB(value16); break;
            //case 0x0F:

            case 0x10: Instructions.BPL_REL(value8); break;
            case 0x11: Instructions.ORA_IDY(value8); break;
            //case 0x12:
            //case 0x13:
            //case 0x14:
            case 0x15: Instructions.ORA_ZPX(value8); break;
            case 0x16: Instructions.ASL_ZPX(value8); break;
            //case 0x17:
            case 0x18: Instructions.CLC_IMP(); break;
            case 0x19: Instructions.ORA_ABY(value16); break;
            //case 0x1A:
            //case 0x1B:
            //case 0x1C:
            case 0x1D: Instructions.ORA_ABX(value16); break;
            case 0x1E: Instructions.ASL_ABX(value16); break;
            //case 0x1F:

            case 0x20: Instructions.JSR_AB(value16); break;
            case 0x21: Instructions.AND_IDX(value8); break;
            //case 0x22:
            //case 0x23:
            case 0x24: Instructions.BIT_ZP(value8); break;
            case 0x25: Instructions.AND_ZP(value8); break;
            case 0x26: Instructions.ROL_ZP(value8); break;
            //case 0x27:
            case 0x28: Instructions.PLP_IMP(); break;
            case 0x29: Instructions.AND_IMM(value8); break;
            case 0x2A: Instructions.ROL_ACC(); break;
            //case 0x2B:
            case 0x2C: Instructions.BIT_AB(value16); break;
            case 0x2D: Instructions.AND_AB(value16); break;
            case 0x2E: Instructions.ROL_AB(value16); break;
            //case 0x2F:

            case 0x30: Instructions.BMI_REL(value8); break;
            case 0x31: Instructions.AND_IDY(value8); break;
            //case 0x32:
            //case 0x33:
            //case 0x34:
            case 0x35: Instructions.AND_ZPX(value8); break;
            case 0x36: Instructions.ROL_ZPX(value8); break;
            //case 0x37:
            case 0x38: Instructions.SEC_IMP(); break;
            case 0x39: Instructions.AND_ABY(value16); break;
            //case 0x3A:
            //case 0x3B:
            //case 0x3C:
            case 0x3D: Instructions.AND_ABX(value16); break;
            case 0x3E: Instructions.ROL_ABX(value16); break;
            //case 0x3F:

            case 0x40: Instructions.RTI_IMP(); break;
            case 0x41: Instructions.EOR_IDX(value8); break;
            //case 0x42:
            //case 0x43:
            //case 0x44:
            case 0x45: Instructions.EOR_ZP(value8); break;
            case 0x46: Instructions.LSR_ZP(value8); break;
            //case 0x47:
            case 0x48: Instructions.PHA_IMP(); break;
            case 0x49: Instructions.EOR_IMM(value8); break;
            case 0x4A: Instructions.LSR_ACC(); break;
            //case 0x4B:
            case 0x4C: Instructions.JMP_AB(value16); break;
            case 0x4D: Instructions.EOR_AB(value16); break;
            case 0x4E: Instructions.LSR_AB(value16); break;
            //case 0x4F:

            case 0x50: Instructions.BVC_REL(value8); break;
            case 0x51: Instructions.EOR_IDY(value8); break;
            //case 0x52:
            //case 0x53:
            //case 0x54:
            case 0x55: Instructions.EOR_ZPX(value8); break;
            case 0x56: Instructions.LSR_ZPX(value8); break;
            //case 0x57:
            case 0x58: Instructions.CLI_IMP(); break;
            case 0x59: Instructions.EOR_ABY(value16); break;
            //case 0x5A:
            //case 0x5B:
            //case 0x5C:
            case 0x5D: Instructions.LSR_ABX(value16); break;
            case 0x5E: Instructions.LSR_ABX(value16); break;
            //case 0x5F:

            case 0x60: Instructions.RTS_IMP(); break;
            case 0x61: Instructions.ADC_IDX(value8); break;
            //case 0x62:
            //case 0x63:
            //case 0x64:
            case 0x65: Instructions.ADC_ZP(value8); break;
            case 0x66: Instructions.ROR_ZP(value8); break;
            //case 0x67:
            case 0x68: Instructions.PLA_IMP(); break;
            case 0x69: Instructions.ADC_IMM(value8); break;
            case 0x6A: Instructions.ROR_ACC(); break;
            //case 0x6B:
            case 0x6C: Instructions.JMP_AB(value16); break;
            case 0x6D: Instructions.ADC_AB(value16); break;
            case 0x6E: Instructions.ROR_AB(value16); break;
            //case 0x6F:

            case 0x70: Instructions.BVS_REL(value8); break;
            case 0x71: Instructions.ADC_IDY(value8); break;
            //case 0x72:
            //case 0x73:
            //case 0x74:
            case 0x75: Instructions.ADC_ZPX(value8); break;
            case 0x76: Instructions.ROR_ZPX(value8); break;
            //case 0x77:
            case 0x78: Instructions.SEI_IMP(); break;
            case 0x79: Instructions.ADC_ABY(value16); break;
            //case 0x7A:
            //case 0x7B:
            //case 0x7C:
            case 0x7D: Instructions.ADC_ABY(value16); break;
            case 0x7E: Instructions.ROR_ABX(value16); break;
            //case 0x7F:

            //case 0x80:
            case 0x81: Instructions.STA_IDX(value8); break;
            //case 0x82:
            //case 0x83:
            case 0x84: Instructions.STY_ZP(value8); break;
            case 0x85: Instructions.STA_ZP(value8); break;
            case 0x86: Instructions.STX_ZP(value8); break;
            //case 0x87:
            case 0x88: Instructions.DEY_IMP(); break;
            //case 0x89:
            case 0x8A: Instructions.TXA_IMP(); break;
            //case 0x8B:
            case 0x8C: Instructions.STY_AB(value16); break;
            case 0x8D: Instructions.STA_AB(value16); break;
            case 0x8E: Instructions.STX_AB(value16); break;
            //case 0x8F:

            case 0x90: Instructions.BCC_REL(value8); break;
            case 0x91: Instructions.STA_IDY(value8); break;
            //case 0x92:
            //case 0x93:
            case 0x94: Instructions.STY_ZPX(value8); break;
            case 0x95: Instructions.STA_ZPX(value8); break;
            case 0x96: Instructions.STX_ZPY(value8); break;
            //case 0x97:
            case 0x98: Instructions.TYA_IMP(); break;
            case 0x99: Instructions.STA_ABY(value16); break;
            case 0x9A: Instructions.TXS_IMP(); break;
            //case 0x9B:
            //case 0x9C:
            case 0x9D: Instructions.STA_ABX(value16); break;
            //case 0x9E:
            //case 0x9F:

            case 0xA0: Instructions.LDY_IMM(value8); break;
            case 0xA1: Instructions.LDA_IDX(value8); break;
            case 0xA2: Instructions.LDX_IMM(value8); break;
            //case 0xA3:
            case 0xA4: Instructions.LDY_ZP(value8); break;
            case 0xA5: Instructions.LDA_ZP(value8); break;
            case 0xA6: Instructions.LDX_ZP(value8); break;
            //case 0xA7:
            case 0xA8: Instructions.TAY_IMP(); break;
            case 0xA9:
            case 0xAA:
            //case 0xAB:
            case 0xAC:
            case 0xAD: Instructions.LDA_AB(value16); break;
            case 0xAE:
            //case 0xAF:

            case 0xB0:
            case 0xB1:
            //case 0xB2:
            //case 0xB3:
            case 0xB4:
            case 0xB5:
            case 0xB6:
            //case 0xB7:
            case 0xB8:
            case 0xB9:
            case 0xBA:
            //case 0xBB:
            case 0xBC:
            case 0xBD:
            case 0xBE:
            //case 0xBF:

            case 0xC0:
            case 0xC1:
            //case 0xC2:
            //case 0xC3:
            case 0xC4:
            case 0xC5:
            case 0xC6:
            //case 0xC7:
            case 0xC8:
            case 0xC9:
            case 0xCA:
            //case 0xCB:
            case 0xCC:
            case 0xCD:
            case 0xCE:
            //case 0xCF:

            case 0xD0:
            case 0xD1:
            //case 0xD2:
            //case 0xD3:
            //case 0xD4:
            case 0xD5:
            case 0xD6:
            //case 0xD7:
            case 0xD8:
            case 0xD9:
            //case 0xDA:
            //case 0xDB:
            //case 0xDC:
            case 0xDD:
            case 0xDE:
            //case 0xDF:

            case 0xE0:
            case 0xE1:
            //case 0xE2:
            //case 0xE3:
            case 0xE4:
            case 0xE5:
            case 0xE6:
            //case 0xE7:
            case 0xE8:
            case 0xE9:
            case 0xEA:
            //case 0xEB:
            case 0xEC:
            case 0xED:
            case 0xEE:
            //case 0xEF:

            case 0xF0:
            case 0xF1:
            //case 0xF2:
            //case 0xF3:
            //case 0xF4:
            case 0xF5:
            case 0xF6:
            //case 0xF7:
            case 0xF8:
            case 0xF9:
            //case 0xFA:
            //case 0xFB:
            //case 0xFC:
            case 0xFD:
            case 0xFE:
            //case 0xFF:
            default: System.err.println("InvalidCPUInstructionException, decoded instruction not available in 6502 Processor." + Integer.toHexString(opCode));

        }
	}


}