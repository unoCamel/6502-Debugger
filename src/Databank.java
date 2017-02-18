import java.util.*;

public class Databank {
    /*Modes will be portrayed as following in a 13bit number.
    *
    * (1) Implicit / (2) Accumulator / (3) Immediate / (4) Zero Page / (5) Zero Page,X / (6) Zero Page,Y / (7) Relative
    * / (8) Absolute / (9) Absolute,X / (10) Absolute,Y / (11) Indirect / (12) (Indirect,X) / (13) (Indirect,Y)
    *
     */
//    static private int modeADC, modeAND, modeASL, modeBCC, modeBCS, modeBEQ, modeBIT, modeBMI, modeBNE, modeBPL, modeBRK, modeBVC, modeBVS, modeCLC,
//                       modeCLD, modeCLI, modeCLV, modeCMP, modeCPX, modeCPY, modeDEC, modeDEX, modeDEY, modeEOR, modeINC, modeINX, modeINY, modeJMP,
//                       modeJSR, modeLDA, modeLDX, modeLDY, modeLSR, modeNOP, modeORA, modePHA, modePHP, modePLA, modePLP, modeROL, modeROR, modeRTI,
//                       modeRTS, modeSBC, modeSEC, modeSED, modeSEI, modeSTA, modeSTX, modeSTY, modeTAX, modeTAY, modeTSX, modeTXA, modeTXS, modeTYA;

    static private Integer[] opcodesADC, opcodesAND, opcodesASL, opcodesBCC, opcodesBCS, opcodesBEQ, opcodesBIT, opcodesBMI, opcodesBNE, opcodesBPL, opcodesBRK, opcodesBVC, opcodesBVS, opcodesCLC,
                         opcodesCLD, opcodesCLI, opcodesCLV, opcodesCMP, opcodesCPX, opcodesCPY, opcodesDEC, opcodesDEX, opcodesDEY, opcodesEOR, opcodesINC, opcodesINX, opcodesINY, opcodesJMP,
                         opcodesJSR, opcodesLDA, opcodesLDX, opcodesLDY, opcodesLSR, opcodesNOP, opcodesORA, opcodesPHA, opcodesPHP, opcodesPLA, opcodesPLP, opcodesROL, opcodesROR, opcodesRTI,
                         opcodesRTS, opcodesSBC, opcodesSEC, opcodesSED, opcodesSEI, opcodesSTA, opcodesSTX, opcodesSTY, opcodesTAX, opcodesTAY, opcodesTSX, opcodesTXA, opcodesTXS, opcodesTYA;

//    static private Pair<Integer, Integer[]> pairADC, pairAND, pairASL, pairBCC, pairBCS, pairBEQ, pairBIT, pairBMI, pairBNE, pairBPL, pairBRK, pairBVC, pairBVS, pairCLC,
//                                        pairCLD, pairCLI, pairCLV, pairCMP, pairCPX, pairCPY, pairDEC, pairDEX, pairDEY, pairEOR, pairINC, pairINX, pairINY, pairJMP,
//                                        pairJSR, pairLDA, pairLDX, pairLDY, pairLSR, pairNOP, pairORA, pairPHA, pairPHP, pairPLA, pairPLP, pairROL, pairROR, pairRTI,
//                                        pairRTS, pairSBC, pairSEC, pairSED, pairSEI, pairSTA, pairSTX, pairSTY, pairTAX, pairTAY, pairTSX, pairTXA, pairTXS, pairTYA;

    private static Map<String, Integer[]> binaryAssembler = new HashMap<String, Integer[]>();

    public Databank(){
        // (1) Implicit / (2) Accumulator / (3) Immediate / (4) Zero Page / (5) Zero Page,X / (6) Zero Page,Y / (7) Relative
        // (8) Absolute / (9) Absolute,X / (10) Absolute,Y / (11) Indirect / (12) (Indirect,X) / (13) (Indirect,Y)
                          /*13    12	11	  10	9	  8	    7	  6     5     4	    3	  2     1   MODE= 12   8    4  1*/
        Integer[] opcodesADC = {0x71, 0x61, null, 0x79, 0x7D, 0x6D, null, null, 0x75, 0x65, 0x69, null, null, 0b1_1011_1001_1100};
        Integer[] opcodesAND = {0x31, 0x21, null, 0x39, 0x3D, 0x2D, null, null, 0x35, 0x25, 0x29, null, null, 0b1_1011_1001_1100};
        Integer[] opcodesASL = {null, null, null, null, 0x1E, 0x0E, null, null, 0x16, 0x06, null, 0x0A, null, 0b0_0001_1001_1010};
        Integer[] opcodesBCC = {null, null, null, null, null, null, null, null, null, null, null, null, 0x90, 0b0_0000_0000_0001};
        Integer[] opcodesBCS = {null, null, null, null, null, null, null, null, null, null, null, null, 0xB0, 0b0_0000_0000_0001};
        Integer[] opcodesBEQ = {null, null, null, null, null, null, null, null, null, null, null, null, 0xF0, 0b0_0000_0000_0001};
        Integer[] opcodesBIT = {null, null, null, null, null, 0x2C, null, null, null, 0x24, null, null, null, 0b0_0000_1000_1000};
        Integer[] opcodesBMI = {null, null, null, null, null, null, null, null, null, null, null, null, 0x30, 0b0_0000_0000_0001};
        Integer[] opcodesBNE = {null, null, null, null, null, null, null, null, null, null, null, null, 0xD0, 0b0_0000_0000_0001};
        Integer[] opcodesBPL = {null, null, null, null, null, null, null, null, null, null, null, null, 0x10, 0b0_0000_0000_0001};
        Integer[] opcodesBRK = {null, null, null, null, null, null, null, null, null, null, null, null, 0x00, 0b0_0000_0000_0001};
        Integer[] opcodesBVC = {null, null, null, null, null, null, null, null, null, null, null, null, 0x50, 0b0_0000_0000_0001};
        Integer[] opcodesBVS = {null, null, null, null, null, null, null, null, null, null, null, null, 0x70, 0b0_0000_0000_0001};
        Integer[] opcodesCLC = {null, null, null, null, null, null, null, null, null, null, null, null, 0x18, 0b0_0000_0000_0001};
                          /*13    12	11	  10	9	  8	    7	  6     5     4	    3	  2     1   MODE= 12   8    4  1*/
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
                          /*13    12	11	  10	9	  8	    7	  6     5     4	    3	  2     1   MODE= 12   8    4  1*/
        Integer[] opcodesJSR = {null, null, null, null, null, 0x20, null, null, null, null, null, null, null, 0b0_0000_1000_0000};
        Integer[] opcodesLDA = {0xB1, 0xA1, null, 0xB9, 0xBD, 0xAD, null, null, 0xB5, 0xA5, 0xA9, null, null, 0b1_1011_1001_1100};
        Integer[] opcodesLDX = {null, null, null, 0xBE, null, 0xAE, null, 0xB6, null, 0xA6, 0xA2, null, null, 0b0_0010_1010_1100};
        Integer[] opcodesLDY = {null, null, null, null, 0xBC, 0xAC, null, null, 0xB4, 0xA4, 0xA0, null, null, 0b0_0001_1001_1100};
        Integer[] opcodesLSR = {null, null, null, null, 0x5E, 0x4E, null, null, 0x56, 0x46, null, 0x4A, null, 0b0_0001_1001_1010};
        Integer[] opcodesNOP = {null, null, null, null, null, null, null, null, null, null, null, null, 0xEA, 0b0_0000_0000_0001};
        Integer[] opcodesORA = {0x11, 0x01, null, 0x19, 0x1D, 0x0D, null, null, 0x15, 0x05, 0x09, null, null, 0b1_1011_1001_1100};
        Integer[] opcodesPHA = {null, null, null, null, null, null, null, null, null, null, null, null, 0x01, 0b0_0000_0000_0001};
        Integer[] opcodesPHP = {null, null, null, null, null, null, null, null, null, null, null, null, 0x08, 0b0_0000_0000_0001};
        Integer[] opcodesPLA = {null, null, null, null, null, null, null, null, null, null, null, null, 0x68, 0b0_0000_0000_0001};
        Integer[] opcodesPLP = {null, null, null, null, null, null, null, null, null, null, null, null, 0x28, 0b0_0000_0000_0001};
        Integer[] opcodesROL = {null, null, null, null, 0x3E, 0x2E, null, null, 0x36, 0x26, null, 0x2A, null, 0b0_0001_1001_1010};
        Integer[] opcodesROR = {null, null, null, null, 0x7E, 0x6E, null, null, 0x76, 0x66, null, 0x6A, null, 0b0_0001_1001_1010};
        Integer[] opcodesRTI = {null, null, null, null, null, null, null, null, null, null, null, null, 0x40, 0b0_0000_0000_0001};
        //TODO
                          /*13    12	11	  10	9	  8	    7	  6     5     4	    3	  2     1   MODE= 12   8    4  1*/
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

        //        modeAND = 0b0_0000_0000_0000; Integer[] opcodesAND = {};
//        modeASL = 0b0_0000_0000_0000; Integer[] opcodesASL = {};
//        modeBCC = 0b0_0000_0000_0000; Integer[] opcodesBCC = {};
//        modeBCS = 0b0_0000_0000_0000; Integer[] opcodesBCS = {};
//        modeBEQ = 0b0_0000_0000_0000; Integer[] opcodesBEQ = {};
//        modeBIT = 0b0_0000_0000_0000; Integer[] opcodesBIT = {};
//        modeBMI = 0b0_0000_0000_0000; Integer[] opcodesBMI = {};
//        modeBNE = 0b0_0000_0000_0000; Integer[] opcodesBNE = {};
//        modeBPL = 0b0_0000_0000_0000; Integer[] opcodesBPL = {};
//        modeBRK = 0b0_0000_0000_0000; Integer[] opcodesBRK = {};
//        modeBVC = 0b0_0000_0000_0000; Integer[] opcodesBVC = {};
//        modeBVS = 0b0_0000_0000_0000; Integer[] opcodesBVS = {};
//        modeCLC = 0b0_0000_0000_0000; Integer[] opcodesCLC = {};
//
//        modeCLD = 0b0_0000_0000_0000; Integer[] opcodesCLD = {};
//        modeCLI = 0b0_0000_0000_0000; Integer[] opcodesCLI = {};
//        modeCLV = 0b0_0000_0000_0000; Integer[] opcodesCLV = {};
//        modeCMP = 0b0_0000_0000_0000; Integer[] opcodesCMP = {};
//        modeCPX = 0b0_0000_0000_0000; Integer[] opcodesCPX = {};
//        modeCPY = 0b0_0000_0000_0000; Integer[] opcodesCPY = {};
//        modeDEC = 0b0_0000_0000_0000; Integer[] opcodesDEC = {};
//        modeDEX = 0b0_0000_0000_0000; Integer[] opcodesDEX = {};
//        modeDEY = 0b0_0000_0000_0000; Integer[] opcodesDEY = {};
//        modeEOR = 0b0_0000_0000_0000; Integer[] opcodesEOR = {};
//        modeINC = 0b0_0000_0000_0000; Integer[] opcodesINC = {};
//        modeINX = 0b0_0000_0000_0000; Integer[] opcodesINX = {};
//        modeINY = 0b0_0000_0000_0000; Integer[] opcodesINY = {};
//        modeJMP = 0b0_0000_0000_0000; Integer[] opcodesJMP = {};
//
//        modeJSR = 0b0_0000_0000_0000; Integer[] opcodesJSR = {};
//        modeLDA = 0b0_0000_0000_0000; Integer[] opcodesLDA = {};
//        modeLDX = 0b0_0000_0000_0000; Integer[] opcodesLDX = {};
//        modeLDY = 0b0_0000_0000_0000; Integer[] opcodesLDY = {};
//        modeLSR = 0b0_0000_0000_0000; Integer[] opcodesLSR = {};
//        modeNOP = 0b0_0000_0000_0000; Integer[] opcodesNOP = {};
//        modeORA = 0b0_0000_0000_0000; Integer[] opcodesORA = {};
//        modePHA = 0b0_0000_0000_0000; Integer[] opcodesPHA = {};
//        modePHP = 0b0_0000_0000_0000; Integer[] opcodesPHP = {};
//        modePLA = 0b0_0000_0000_0000; Integer[] opcodesPLA = {};
//        modePLP = 0b0_0000_0000_0000; Integer[] opcodesPLP = {};
//        modeROL = 0b0_0000_0000_0000; Integer[] opcodesROL = {};
//        modeROR = 0b0_0000_0000_0000; Integer[] opcodesROR = {};
//        modeRTI = 0b0_0000_0000_0000; Integer[] opcodesRTI = {};
//
//        modeRTS = 0b0_0000_0000_0000; Integer[] opcodesRTS = {};
//        modeSBC = 0b0_0000_0000_0000; Integer[] opcodesSBC = {};
//        modeSEC = 0b0_0000_0000_0000; Integer[] opcodesSEC = {};
//        modeSED = 0b0_0000_0000_0000; Integer[] opcodesSED = {};
//        modeSEI = 0b0_0000_0000_0000; Integer[] opcodesSEI = {};
//        modeSTA = 0b0_0000_0000_0000; Integer[] opcodesSTA = {};
//        modeSTX = 0b0_0000_0000_0000; Integer[] opcodesSTX = {};
//        modeSTY = 0b0_0000_0000_0000; Integer[] opcodesSTY = {};
//        modeTAX = 0b0_0000_0000_0000; Integer[] opcodesTAX = {};
//        modeTAY = 0b0_0000_0000_0000; Integer[] opcodesTAY = {};
//        modeTSX = 0b0_0000_0000_0000; Integer[] opcodesTSX = {};
//        modeTXA = 0b0_0000_0000_0000; Integer[] opcodesTXA = {};
//        modeTXS = 0b0_0000_0000_0000; Integer[] opcodesTXS = {};
//        modeTYA = 0b0_0000_0000_0000; Integer[] opcodesTYA = {};

    }
    /*
        TODO, incorrect logic probably (UNTESTED)
        @brief Returns the specific opcode of the instruction, given the mode as well.
        "LD A B \n ADC A B \n LD A"
        @param instruction A String representation of the instruction
        @param mode a 13-bit number, specifying what mode it is.
        @return a 8-bit number, specifying the specific opcode.
    */
    public static int getOPCode(String instruction, int mode){
        Integer[] temp = binaryAssembler.get(instruction);
        return temp[(mode + 12) % 13];
    }

    public static void toString(Map<String, Integer[]> map) {
        System.out.println(map.toString());
    }



//
//	/* @brief Class to combine generic types together.
//	*/
//    private static class Pair<K, V> {
//
//        private final K modes;
//        private final V opcodes;
//
//        /* @brief Create a Pair, provide two types.
//        *
//        * @param modes
//        * @param opcodes
//        * @return Pair<K, V>.
//        */
//        private static <K, V> Pair<K, V> createPair(K modes, V opcodes) {
//            return new Pair<K, V>(modes, opcodes);
//        }
//
//        private Pair(K modes, V opcodes) {
//            this.modes = modes;
//            this.opcodes = opcodes;
//        }
//
//        public K getModes() {
//            return modes;
//        }
//
//        public V getOpcodes() {
//            return opcodes;
//        }
//    }

//    public static void main(String[] args) {
//
//        System.out.println("Hello, World");
//        Databank db = new Databank();
//        Databank.toString(db.binaryAssembler);
//    }

}


