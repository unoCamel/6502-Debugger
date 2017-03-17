import java.util.*;

public class Databank {
    /*Modes will be portrayed as following in a 13bit number.
    *
    * (1) Implicit / (2) Accumulator / (3) Immediate / (4) Zero Page / (5) Zero Page,X / (6) Zero Page,Y / (7) Relative
    * / (8) Absolute / (9) Absolute,X / (10) Absolute,Y / (11) Indirect / (12) (Indirect,X) / (13) (Indirect,Y)
    *
     */

    static private Integer[] opcodesADC, opcodesAND, opcodesASL, opcodesBCC, opcodesBCS, opcodesBEQ, opcodesBIT, opcodesBMI, opcodesBNE, opcodesBPL, opcodesBRK, opcodesBVC, opcodesBVS, opcodesCLC,
                         opcodesCLD, opcodesCLI, opcodesCLV, opcodesCMP, opcodesCPX, opcodesCPY, opcodesDEC, opcodesDEX, opcodesDEY, opcodesEOR, opcodesINC, opcodesINX, opcodesINY, opcodesJMP,
                         opcodesJSR, opcodesLDA, opcodesLDX, opcodesLDY, opcodesLSR, opcodesNOP, opcodesORA, opcodesPHA, opcodesPHP, opcodesPLA, opcodesPLP, opcodesROL, opcodesROR, opcodesRTI,
                         opcodesRTS, opcodesSBC, opcodesSEC, opcodesSED, opcodesSEI, opcodesSTA, opcodesSTX, opcodesSTY, opcodesTAX, opcodesTAY, opcodesTSX, opcodesTXA, opcodesTXS, opcodesTYA;

    private static Map<String, Integer[]> binaryAssembler = new HashMap<String, Integer[]>();

    public Databank(){
        // (1) Implicit / (2) Accumulator / (3) Immediate / (4) Zero Page / (5) Zero Page,X / (6) Zero Page,Y / (7) Relative
        // (8) Absolute / (9) Absolute,X / (10) Absolute,Y / (11) Indirect / (12) (Indirect,X) / (13) (Indirect,Y)
                                /*13  12    11	  10	9	  8	    7	  6     5     4	    3	  2     1   MODE= 12   8    4  1*/
        Integer[] opcodesADC = {0x71, 0x61, null, 0x79, 0x7D, 0x6D, null, null, 0x75, 0x65, 0x69, null, null, 0b1_1011_1001_1100};
        Integer[] opcodesAND = {0x31, 0x21, null, 0x39, 0x3D, 0x2D, null, null, 0x35, 0x25, 0x29, null, null, 0b1_1011_1001_1100};
        Integer[] opcodesASL = {null, null, null, null, 0x1E, 0x0E, null, null, 0x16, 0x06, null, 0x0A, null, 0b0_0001_1001_1010};
        Integer[] opcodesBCC = {null, null, null, null, null, null, 0x90, null, null, null, null, null, null, 0b0_0000_0100_0000};
        Integer[] opcodesBCS = {null, null, null, null, null, null, 0xB0, null, null, null, null, null, null, 0b0_0000_0100_0000};
        Integer[] opcodesBEQ = {null, null, null, null, null, null, 0xF0, null, null, null, null, null, 0xF0, 0b0_0000_0100_0000};
        Integer[] opcodesBIT = {null, null, null, null, null, 0x2C, null, null, null, 0x24, null, null, null, 0b0_0000_1000_1000};
        Integer[] opcodesBMI = {null, null, null, null, null, null, 0x30, null, null, null, null, null, null, 0b0_0000_0100_0000};
        Integer[] opcodesBNE = {null, null, null, null, null, null, 0xD0, null, null, null, null, null, null, 0b0_0000_0100_0000};
        Integer[] opcodesBPL = {null, null, null, null, null, null, 0x10, null, null, null, null, null, null, 0b0_0000_0100_0000};
        Integer[] opcodesBRK = {null, null, null, null, null, null, null, null, null, null, null, null, 0x00, 0b0_0000_0100_0000};
        Integer[] opcodesBVC = {null, null, null, null, null, null, 0x50, null, null, null, null, null, null, 0b0_0000_0100_0000};
        Integer[] opcodesBVS = {null, null, null, null, null, null, 0x70, null, null, null, null, null, null, 0b0_0000_0100_0000};
        Integer[] opcodesCLC = {null, null, null, null, null, null, null, null, null, null, null, null, 0x18, 0b0_0000_0000_0001};
                                /*13  12    11	  10	9	  8	    7	  6     5     4	    3	  2     1   MODE= 12   8    4  1*/
        Integer[] opcodesCLD = {null, null, null, null, null, null, null, null, null, null, null, null, 0XD8, 0b0_0000_0000_0001};
        Integer[] opcodesCLI = {null, null, null, null, null, null, null, null, null, null, null, null, 0X58, 0b0_0000_0000_0001};
        Integer[] opcodesCLV = {null, null, null, null, null, null, null, null, null, null, null, null, 0XB8, 0b0_0000_0000_0001};
        Integer[] opcodesCMP = {0xD1, 0xC1, null, 0xD9, 0xDD, 0xCD, null, null, 0xD5, 0xC5, 0xC9, null, null, 0b1_1011_1001_1100};
        Integer[] opcodesCPX = {null, null, null, null, null, 0xEC, null, null, null, 0xE4, 0xE0, null, null, 0b0_0000_1000_1100};
        Integer[] opcodesCPY = {null, null, null, null, null, 0xCC, null, null, null, 0xC4, 0xC0, null, null, 0b0_0000_1000_1100};
        Integer[] opcodesDEC = {null, null, null, null, 0xDE, 0xCE, null, null, 0xD6, 0xC6, null, null, null, 0b0_0001_1001_1000};
        Integer[] opcodesDEX = {null, null, null, null, null, null, null, null, null, null, null, null, 0xCA, 0b0_0000_0000_0001};
        Integer[] opcodesDEY = {null, null, null, null, null, null, null, null, null, null, null, null, 0x88, 0b0_0000_0000_0001};
        Integer[] opcodesEOR = {0x51, 0x41, null, 0x59, 0x5D, 0x4D, null, null, 0x55, 0x45, 0x49, null, null, 0b1_1011_1001_1100};
        Integer[] opcodesINC = {null, null, null, null, 0xFE, 0xEE, null, null, 0xF6, 0xE6, null, null, null, 0b0_0001_1001_1000};
        Integer[] opcodesINX = {null, null, null, null, null, null, null, null, null, null, null, null, 0xE8, 0b0_0000_0000_0001};
        Integer[] opcodesINY = {null, null, null, null, null, null, null, null, null, null, null, null, 0xC8, 0b0_0000_0000_0001};
        Integer[] opcodesJMP = {null, null, 0x6C, null, null, 0x4C, null, null, null, null, null, null, null, 0b0_0100_1000_0000};
                                /*13  12    11	  10	9	  8	    7	  6     5     4	    3	  2     1   MODE= 12   8    4  1*/
        Integer[] opcodesJSR = {null, null, null, null, null, 0x20, null, null, null, null, null, null, null, 0b0_0000_1000_0000};
        Integer[] opcodesLDA = {0xB1, 0xA1, null, 0xB9, 0xBD, 0xAD, null, null, 0xB5, 0xA5, 0xA9, null, null, 0b1_1011_1001_1100};
        Integer[] opcodesLDX = {null, null, null, 0xBE, null, 0xAE, null, 0xB6, null, 0xA6, 0xA2, null, null, 0b0_0010_1010_1100};
        Integer[] opcodesLDY = {null, null, null, null, 0xBC, 0xAC, null, null, 0xB4, 0xA4, 0xA0, null, null, 0b0_0001_1001_1100};
        Integer[] opcodesLSR = {null, null, null, null, 0x5E, 0x4E, null, null, 0x56, 0x46, null, 0x4A, null, 0b0_0001_1001_1010};
        Integer[] opcodesNOP = {null, null, null, null, null, null, null, null, null, null, null, null, 0xEA, 0b0_0000_0000_0001};
        Integer[] opcodesORA = {0x11, 0x01, null, 0x19, 0x1D, 0x0D, null, null, 0x15, 0x05, 0x09, null, null, 0b1_1011_1001_1100};
        Integer[] opcodesPHA = {null, null, null, null, null, null, null, null, null, null, null, null, 0x48, 0b0_0000_0000_0001};
        Integer[] opcodesPHP = {null, null, null, null, null, null, null, null, null, null, null, null, 0x08, 0b0_0000_0000_0001};
        Integer[] opcodesPLA = {null, null, null, null, null, null, null, null, null, null, null, null, 0x68, 0b0_0000_0000_0001};
        Integer[] opcodesPLP = {null, null, null, null, null, null, null, null, null, null, null, null, 0x28, 0b0_0000_0000_0001};
        Integer[] opcodesROL = {null, null, null, null, 0x3E, 0x2E, null, null, 0x36, 0x26, null, 0x2A, null, 0b0_0001_1001_1010};
        Integer[] opcodesROR = {null, null, null, null, 0x7E, 0x6E, null, null, 0x76, 0x66, null, 0x6A, null, 0b0_0001_1001_1010};
        Integer[] opcodesRTI = {null, null, null, null, null, null, null, null, null, null, null, null, 0x40, 0b0_0000_0000_0001};
        //TODO
                                /*13  12    11	  10	9	  8	    7	  6     5     4	    3	  2     1   MODE= 12   8    4  1*/
        Integer[] opcodesRTS = {null, null, null, null, null, null, null, null, null, null, null, null, 0x60, 0b0_0000_0000_0001};
        Integer[] opcodesSBC = {0xF1, 0xE1, null, 0xF9, 0xFD, 0xED, null, null, 0xF5, 0xE5, 0xE9, null, null, 0b1_1011_1001_1100};
        Integer[] opcodesSEC = {null, null, null, null, null, null, null, null, null, null, null, null, 0x38, 0b0_0000_0000_0001};
        Integer[] opcodesSED = {null, null, null, null, null, null, null, null, null, null, null, null, 0xF8, 0b0_0000_0000_0001};
        Integer[] opcodesSEI = {null, null, null, null, null, null, null, null, null, null, null, null, 0x78, 0b0_0000_0000_0001};
        Integer[] opcodesSTA = {0x91, 0x81, null, 0x99, 0x9D, 0x8D, null, null, 0x95, 0x85, null, null, null, 0b1_1011_1001_1000};
        Integer[] opcodesSTX = {null, null, null, null, null, 0x8E, null, 0x96, null, 0x86, null, null, null, 0b0_0000_1010_1000};
        Integer[] opcodesSTY = {null, null, null, null, null, 0x8C, null, null, 0x94, 0x84, null, null, null, 0b0_0000_1001_1000};
        Integer[] opcodesTAX = {null, null, null, null, null, null, null, null, null, null, null, null, 0xAA, 0b0_0000_0000_0001};
        Integer[] opcodesTAY = {null, null, null, null, null, null, null, null, null, null, null, null, 0xA8, 0b0_0000_0000_0001};
        Integer[] opcodesTSX = {null, null, null, null, null, null, null, null, null, null, null, null, 0xBA, 0b0_0000_0000_0001};
        Integer[] opcodesTXA = {null, null, null, null, null, null, null, null, null, null, null, null, 0x8A, 0b0_0000_0000_0001};
        Integer[] opcodesTXS = {null, null, null, null, null, null, null, null, null, null, null, null, 0x9A, 0b0_0000_0000_0001};
        Integer[] opcodesTYA = {null, null, null, null, null, null, null, null, null, null, null, null, 0x98, 0b0_0000_0000_0001};

//        binaryAssembler = new Map<String, Integer[]>();
        binaryAssembler.put("ADC", opcodesADC);
        binaryAssembler.put("AND", opcodesAND);
        binaryAssembler.put("ASL", opcodesASL);
        binaryAssembler.put("BCC", opcodesBCC);
        binaryAssembler.put("BCS", opcodesBCS);
        binaryAssembler.put("BEQ", opcodesBEQ);
        binaryAssembler.put("BIT", opcodesBIT);
        binaryAssembler.put("BMI", opcodesBMI);
        binaryAssembler.put("BNE", opcodesBNE);
        binaryAssembler.put("BPL", opcodesBPL);
        binaryAssembler.put("BRK", opcodesBRK);
        binaryAssembler.put("BVC", opcodesBVC);
        binaryAssembler.put("BVS", opcodesBVS);
        binaryAssembler.put("CLC", opcodesCLC);
        binaryAssembler.put("CLD", opcodesCLD);
        binaryAssembler.put("CLI", opcodesCLI);
        binaryAssembler.put("CLV", opcodesCLV);
        binaryAssembler.put("CMP", opcodesCMP);
        binaryAssembler.put("CPX", opcodesCPX);
        binaryAssembler.put("CPY", opcodesCPY);
        binaryAssembler.put("DEC", opcodesDEC);
        binaryAssembler.put("DEX", opcodesDEX);
        binaryAssembler.put("DEY", opcodesDEY);
        binaryAssembler.put("EOR", opcodesEOR);
        binaryAssembler.put("INC", opcodesINC);
        binaryAssembler.put("INX", opcodesINX);
        binaryAssembler.put("INY", opcodesINY);
        binaryAssembler.put("JMP", opcodesJMP);
        binaryAssembler.put("JSR", opcodesJSR);
        binaryAssembler.put("LDA", opcodesLDA);
        binaryAssembler.put("LDX", opcodesLDX);
        binaryAssembler.put("LDY", opcodesLDY);
        binaryAssembler.put("LSR", opcodesLSR);
        binaryAssembler.put("NOP", opcodesNOP);
        binaryAssembler.put("ORA", opcodesORA);
        binaryAssembler.put("PHA", opcodesPHA);
        binaryAssembler.put("PHP", opcodesPHP);
        binaryAssembler.put("PLA", opcodesPLA);
        binaryAssembler.put("PLP", opcodesPLP);
        binaryAssembler.put("ROL", opcodesROL);
        binaryAssembler.put("ROR", opcodesROR);
        binaryAssembler.put("RTI", opcodesRTI);
        binaryAssembler.put("RTS", opcodesRTS);
        binaryAssembler.put("SBC", opcodesSBC);
        binaryAssembler.put("SEC", opcodesSEC);
        binaryAssembler.put("SED", opcodesSED);
        binaryAssembler.put("SEI", opcodesSEI);
        binaryAssembler.put("STA", opcodesSTA);
        binaryAssembler.put("STX", opcodesSTX);
        binaryAssembler.put("STY", opcodesSTY);
        binaryAssembler.put("TAX", opcodesTAX);
        binaryAssembler.put("TAY", opcodesTAY);
        binaryAssembler.put("TSX", opcodesTSX);
        binaryAssembler.put("TXA", opcodesTXA);
        binaryAssembler.put("TXS", opcodesTXS);
        binaryAssembler.put("TYA", opcodesTYA);

    }
    /*@brief Returns the specific opcode of the instruction, given the mode as well.
     *"LD A B \n ADC A B \n LD A"
     *@param instruction A String representation of the instruction
     *@param mode a 13-bit number, specifying what mode it is.
     *@return a 8-bit number, specifying the specific opcode.
    */
    public static Integer getOPCode(String instruction, int mode){
        Integer[] temp = binaryAssembler.get(instruction);
        Integer[] opCodeArray = new Integer[12];
        System.arraycopy(temp, 0, opCodeArray, 0, 12);
        //System.out.println(Arrays.toString(opCodeArray));
        return temp[((12-mode)+1)];
    }
    /*@brief will print the currently loaded map of opcodes and modes.
	*
	* @param map The map to print.
	* @return void.
	*/
    public static void toString(Map<String, Integer[]> map) {
        System.out.println(map.toString());
    }

    private static Integer[] jumpCodes = {
                                          1, 2, null, null, null, 2, 2, null, 1, 2,                 //0
                                          1, null, null, 3, 3, null, 2, 2, null, null,              //10
                                          null, 2, 2, null, 1, 3, null, null, null, 3, //1d         //20
                                          3, null, 3, 2, null, null, 2, 2, 2, null,//27             //30
                                          1, 2, 1, null, 3, 3, 3, null, 2, 2, //31                  //40
                                          null, null, null, 2, 2, null, 1, 3, null, null, //3b      //50
                                          null, 3, 3, null, 1, 2, null, null, null, 2,  //45        //60
                                          2, null, 1, 2, 1, null, 3, 3, 3, null, //4f               //70
                                          2, 2, null, null, null, 2, 2, null, 1, 3, //59            //80
                                          null, null, null, 3, 3, null, 1, 2, null, null,  //63     //90
                                          null, 2, 2, null, 1, 2, 1, null, 3, 3,   //6d             //100
                                          3, null, 2, 2, null, null, null, 2, 2, null,  //77        //110
                                          1, 3, null, null, null, 3, 3, null, null, 2,  //81        //120
                                          null, null, 2, 2, 2, null, 1, null, 1, null,  //8b        //130
                                          3, 3, 3, null, 2, 2, null, null, 2, 2,    //95            //140
                                          2, null, 1, 3, 1, null, null, 3, null, null, //9f         //150
                                          2, 2, 2, null, 2, 2, 2, null, 1, 2,  //a9                 //160
                                          1, null, 3, 3, 3, null, 2, 2, null, null,//b3             //170
                                          2, 2, 2, null, 1, 3, 1, null, 3, 3,   //bd                //180
                                          3, null, 2, 2, null, null, 2, 2, 2, null,   //c7          //190
                                          1, 2, 1, null, 3, 3, 3, null, 2, 2, //d1                  //200
                                          null, null, null, 2, 2, null, 1, 3, null, null, //db      //210
                                          null, 3, 3, null, 2, 2, null, null, 2, 2,  //e5           //220
                                          2, null, 1, 2, 1, null, 3, 3, 3, null,  //ef              //230
                                          2, 2, null, null, null, 2, 2, null, 1, 3,    //f9         //240
                                          null, null, null, 3, 3}; //fe                             //250

    /*@brief willgive back the number of increments to the program counter
    *
    * @param int opcode 
    * @return Integer jumpcCode
    */
    public static Integer getJumpCode(int opcode){
        return jumpCodes[opcode];
    }


}


