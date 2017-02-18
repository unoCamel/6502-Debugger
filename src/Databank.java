import jdk.internal.util.xml.impl.Pair;
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

    static private int[] opcodesADC, opcodesAND, opcodesASL, opcodesBCC, opcodesBCS, opcodesBEQ, opcodesBIT, opcodesBMI, opcodesBNE, opcodesBPL, opcodesBRK, opcodesBVC, opcodesBVS, opcodesCLC,
                         opcodesCLD, opcodesCLI, opcodesCLV, opcodesCMP, opcodesCPX, opcodesCPY, opcodesDEC, opcodesDEX, opcodesDEY, opcodesEOR, opcodesINC, opcodesINX, opcodesINY, opcodesJMP,
                         opcodesJSR, opcodesLDA, opcodesLDX, opcodesLDY, opcodesLSR, opcodesNOP, opcodesORA, opcodesPHA, opcodesPHP, opcodesPLA, opcodesPLP, opcodesROL, opcodesROR, opcodesRTI,
                         opcodesRTS, opcodesSBC, opcodesSEC, opcodesSED, opcodesSEI, opcodesSTA, opcodesSTX, opcodesSTY, opcodesTAX, opcodesTAY, opcodesTSX, opcodesTXA, opcodesTXS, opcodesTYA;

//    static private Pair<Integer, int[]> pairADC, pairAND, pairASL, pairBCC, pairBCS, pairBEQ, pairBIT, pairBMI, pairBNE, pairBPL, pairBRK, pairBVC, pairBVS, pairCLC,
//                                        pairCLD, pairCLI, pairCLV, pairCMP, pairCPX, pairCPY, pairDEC, pairDEX, pairDEY, pairEOR, pairINC, pairINX, pairINY, pairJMP,
//                                        pairJSR, pairLDA, pairLDX, pairLDY, pairLSR, pairNOP, pairORA, pairPHA, pairPHP, pairPLA, pairPLP, pairROL, pairROR, pairRTI,
//                                        pairRTS, pairSBC, pairSEC, pairSED, pairSEI, pairSTA, pairSTX, pairSTY, pairTAX, pairTAY, pairTSX, pairTXA, pairTXS, pairTYA;

    private static Map<String, int[]> binaryAssembler;

    public Databank(){
        // (1) Implicit / (2) Accumulator / (3) Immediate / (4) Zero Page / (5) Zero Page,X / (6) Zero Page,Y / (7) Relative
        // (8) Absolute / (9) Absolute,X / (10) Absolute,Y / (11) Indirect / (12) (Indirect,X) / (13) (Indirect,Y)
                          /*13    12	11	  10	9	  8	    7	  6     5     4	    3	  2     1   MODE= 12   8    4  1*/
        int[] opcodesADC = {0x71, 0x61, null, 0x79, 0x7D, 0x6D, null, null, 0x75, 0x65, 0x69, null, null, 0b1_1011_1001_1100};
        int[] opcodesAND = {0x31, 0x21, null, 0x39, 0x3D, 0x2D, null, null, 0x35, 0x25, 0x29, null, null, 0b1_1011_1001_1100};
        int[] opcodesASL = {null, null, null, null, 0x1E, 0x0E, null, null, 0x16, 0x06, null, 0x0A, null, 0b0_0001_1001_1010};
        int[] opcodesBCC = {null, null, null, null, null, null, null, null, null, null, null, null, 0x90, 0b0_0000_0000_0001};
        int[] opcodesBCS = {null, null, null, null, null, null, null, null, null, null, null, null, 0xB0, 0b0_0000_0000_0001};
        int[] opcodesBEQ = {null, null, null, null, null, null, null, null, null, null, null, null, 0xF0, 0b0_0000_0000_0001};
        int[] opcodesBIT = {null, null, null, null, null, 0x2C, null, null, null, 0x24, null, null, null, 0b0_0000_1000_1000};
        int[] opcodesBMI = {null, null, null, null, null, null, null, null, null, null, null, null, 0x30, 0b0_0000_0000_0001};
        int[] opcodesBNE = {null, null, null, null, null, null, null, null, null, null, null, null, 0xD0, 0b0_0000_0000_0001};
        int[] opcodesBPL = {null, null, null, null, null, null, null, null, null, null, null, null, 0x10, 0b0_0000_0000_0001};
        int[] opcodesBRK = {null, null, null, null, null, null, null, null, null, null, null, null, 0x00, 0b0_0000_0000_0001};
        int[] opcodesBVC = {null, null, null, null, null, null, null, null, null, null, null, null, 0x50, 0b0_0000_0000_0001};
        int[] opcodesBVS = {null, null, null, null, null, null, null, null, null, null, null, null, 0x70, 0b0_0000_0000_0001};
        int[] opcodesCLC = {null, null, null, null, null, null, null, null, null, null, null, null, 0x18, 0b0_0000_0000_0001};
                          /*13    12	11	  10	9	  8	    7	  6     5     4	    3	  2     1   MODE= 12   8    4  1*/
        int[] opcodesCLD = {null, null, null, null, null, null, null, null, null, null, null, null, 0XD8, 0b0_0000_0000_0001};
        int[] opcodesCLI = {null, null, null, null, null, null, null, null, null, null, null, null, 0X58, 0b0_0000_0000_0001};
        int[] opcodesCLV = {null, null, null, null, null, null, null, null, null, null, null, null, 0XB8, 0b0_0000_0000_0001};
        int[] opcodesCMP = {0xD1, 0xC1, null, 0xD9, 0xDD, 0xCD, null, null, 0xD5, 0xC5, 0xC9, null, null, 0b1_1011_1001_1100};
        int[] opcodesCPX = {null, null, null, null, null, 0xEC, null, null, null, 0xE4, 0xE0, null, null, 0b0_0000_1000_1100};
        int[] opcodesCPY = {null, null, null, null, null, 0xCC, null, null, null, 0xC4, 0xC0, null, null, 0b0_0000_1000_1100};
        int[] opcodesDEC = {null, null, null, null, 0xDE, 0xCE, null, null, 0xD6, 0xC6, null, null, null, 0b0_0001_1001_1000};
        int[] opcodesDEX = {null, null, null, null, null, null, null, null, null, null, null, null, 0xCA, 0b0_0000_0000_0001};
        int[] opcodesDEY = {null, null, null, null, null, null, null, null, null, null, null, null, 0x88, 0b0_0000_0000_0001};
        int[] opcodesEOR = {0x51, 0x41, null, 0x59, 0x5D, 0x4D, null, null, 0x55, 0x45, 0x49, null, null, 0b1_1011_1001_1100};
        int[] opcodesINC = {null, null, null, null, 0xFE, 0xEE, null, null, 0xF6, 0xE6, null, null, null, 0b0_0001_1001_1000};
        int[] opcodesINX = {null, null, null, null, null, null, null, null, null, null, null, null, 0xE8, 0b0_0000_0000_0001};
        int[] opcodesINY = {null, null, null, null, null, null, null, null, null, null, null, null, 0xC8, 0b0_0000_0000_0001};
        int[] opcodesJMP = {null, null, 0x6C, null, null, 0x4C, null, null, null, null, null, null, null, 0b0_0100_1000_0000};
                          /*13    12	11	  10	9	  8	    7	  6     5     4	    3	  2     1   MODE= 12   8    4  1*/
        int[] opcodesJSR = {null, null, null, null, null, 0x20, null, null, null, null, null, null, null, 0b0_0000_1000_0000};
        int[] opcodesLDA = {0xB1, 0xA1, null, 0xB9, 0xBD, 0xAD, null, null, 0xB5, 0xA5, 0xA9, null, null, 0b1_1011_1001_1100};
        int[] opcodesLDX = {null, null, null, 0xBE, null, 0xAE, null, 0xB6, null, 0xA6, 0xA2, null, null, 0b0_0010_1010_1100};
        int[] opcodesLDY = {null, null, null, null, 0xBC, 0xAC, null, null, 0xB4, 0xA4, 0xA0, null, null, 0b0_0001_1001_1100};
        int[] opcodesLSR = {null, null, null, null, 0x5E, 0x4E, null, null, 0x56, 0x46, null, 0x4A, null, 0b0_0001_1001_1010};
        int[] opcodesNOP = {null, null, null, null, null, null, null, null, null, null, null, null, 0xEA, 0b0_0000_0000_0001};
        int[] opcodesORA = {0x11, 0x01, null, 0x19, 0x1D, 0x0D, null, null, 0x15, 0x05, 0x09, null, null, 0b1_1011_1001_1100};
        int[] opcodesPHA = {null, null, null, null, null, null, null, null, null, null, null, null, 0x01, 0b0_0000_0000_0001};
        int[] opcodesPHP = {null, null, null, null, null, null, null, null, null, null, null, null, 0x08, 0b0_0000_0000_0001};
        int[] opcodesPLA = {null, null, null, null, null, null, null, null, null, null, null, null, 0x68, 0b0_0000_0000_0001};
        int[] opcodesPLP = {null, null, null, null, null, null, null, null, null, null, null, null, 0x28, 0b0_0000_0000_0001};
        int[] opcodesROL = {null, null, null, null, 0x3E, 0x2E, null, null, 0x36, 0x26, null, 0x2A, null, 0b0_0001_1001_1010};
        int[] opcodesROR = {null, null, null, null, 0x7E, 0x6E, null, null, 0x76, 0x66, null, 0x6A, null, 0b0_0001_1001_1010};
        int[] opcodesRTI = {null, null, null, null, null, null, null, null, null, null, null, null, 0x40, 0b0_0000_0000_0001};
        //TODO
                          /*13    12	11	  10	9	  8	    7	  6     5     4	    3	  2     1   MODE= 12   8    4  1*/
        int[] opcodesRTS = {null, null, null, null, null, null, null, null, null, null, null, null, 0x60, 0b0_0000_0000_0001};
        int[] opcodesSBC = {0xF1, 0xE1, null, 0xF9, 0xFD, 0xED, null, null, 0xF5, 0xE5, 0xE9, null, null, 0b1_1011_1001_1100};
        int[] opcodesSEC = {null, null, null, null, null, null, null, null, null, null, null, null, 0x38, 0b0_0000_0000_0001};
        int[] opcodesSED = {null, null, null, null, null, null, null, null, null, null, null, null, 0xF8, 0b0_0000_0000_0001};
        int[] opcodesSEI = {null, null, null, null, null, null, null, null, null, null, null, null, 0x78, 0b0_0000_0000_0001};
        int[] opcodesSTA = {0x91, 0x81, null, 0x99, 0x9D, 0x8D, null, null, 0x95, 0x85, null, null, null, 0b1_1011_1001_1000};
        int[] opcodesSTX = {null, null, null, null, null, 0x8E, null, 0x96, null, 0x86, null, null, null, 0b0_0000_1010_1000};
        int[] opcodesSTY = {null, null, null, null, null, 0x8C, null, null, 0x94, 0x84, null, null, null, 0b0_0000_1001_1000};
        int[] opcodesTAX = {null, null, null, null, null, null, null, null, null, null, null, null, 0xAA, 0b0_0000_0000_0001};
        int[] opcodesTAY = {null, null, null, null, null, null, null, null, null, null, null, null, 0xA8, 0b0_0000_0000_0001};
        int[] opcodesTSX = {null, null, null, null, null, null, null, null, null, null, null, null, 0xBA, 0b0_0000_0000_0001};
        int[] opcodesTXA = {null, null, null, null, null, null, null, null, null, null, null, null, 0x8A, 0b0_0000_0000_0001};
        int[] opcodesTXS = {null, null, null, null, null, null, null, null, null, null, null, null, 0x9A, 0b0_0000_0000_0001};
        int[] opcodesTYA = {null, null, null, null, null, null, null, null, null, null, null, null, 0x98, 0b0_0000_0000_0001};


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

        //        modeAND = 0b0_0000_0000_0000; int[] opcodesAND = {};
//        modeASL = 0b0_0000_0000_0000; int[] opcodesASL = {};
//        modeBCC = 0b0_0000_0000_0000; int[] opcodesBCC = {};
//        modeBCS = 0b0_0000_0000_0000; int[] opcodesBCS = {};
//        modeBEQ = 0b0_0000_0000_0000; int[] opcodesBEQ = {};
//        modeBIT = 0b0_0000_0000_0000; int[] opcodesBIT = {};
//        modeBMI = 0b0_0000_0000_0000; int[] opcodesBMI = {};
//        modeBNE = 0b0_0000_0000_0000; int[] opcodesBNE = {};
//        modeBPL = 0b0_0000_0000_0000; int[] opcodesBPL = {};
//        modeBRK = 0b0_0000_0000_0000; int[] opcodesBRK = {};
//        modeBVC = 0b0_0000_0000_0000; int[] opcodesBVC = {};
//        modeBVS = 0b0_0000_0000_0000; int[] opcodesBVS = {};
//        modeCLC = 0b0_0000_0000_0000; int[] opcodesCLC = {};
//
//        modeCLD = 0b0_0000_0000_0000; int[] opcodesCLD = {};
//        modeCLI = 0b0_0000_0000_0000; int[] opcodesCLI = {};
//        modeCLV = 0b0_0000_0000_0000; int[] opcodesCLV = {};
//        modeCMP = 0b0_0000_0000_0000; int[] opcodesCMP = {};
//        modeCPX = 0b0_0000_0000_0000; int[] opcodesCPX = {};
//        modeCPY = 0b0_0000_0000_0000; int[] opcodesCPY = {};
//        modeDEC = 0b0_0000_0000_0000; int[] opcodesDEC = {};
//        modeDEX = 0b0_0000_0000_0000; int[] opcodesDEX = {};
//        modeDEY = 0b0_0000_0000_0000; int[] opcodesDEY = {};
//        modeEOR = 0b0_0000_0000_0000; int[] opcodesEOR = {};
//        modeINC = 0b0_0000_0000_0000; int[] opcodesINC = {};
//        modeINX = 0b0_0000_0000_0000; int[] opcodesINX = {};
//        modeINY = 0b0_0000_0000_0000; int[] opcodesINY = {};
//        modeJMP = 0b0_0000_0000_0000; int[] opcodesJMP = {};
//
//        modeJSR = 0b0_0000_0000_0000; int[] opcodesJSR = {};
//        modeLDA = 0b0_0000_0000_0000; int[] opcodesLDA = {};
//        modeLDX = 0b0_0000_0000_0000; int[] opcodesLDX = {};
//        modeLDY = 0b0_0000_0000_0000; int[] opcodesLDY = {};
//        modeLSR = 0b0_0000_0000_0000; int[] opcodesLSR = {};
//        modeNOP = 0b0_0000_0000_0000; int[] opcodesNOP = {};
//        modeORA = 0b0_0000_0000_0000; int[] opcodesORA = {};
//        modePHA = 0b0_0000_0000_0000; int[] opcodesPHA = {};
//        modePHP = 0b0_0000_0000_0000; int[] opcodesPHP = {};
//        modePLA = 0b0_0000_0000_0000; int[] opcodesPLA = {};
//        modePLP = 0b0_0000_0000_0000; int[] opcodesPLP = {};
//        modeROL = 0b0_0000_0000_0000; int[] opcodesROL = {};
//        modeROR = 0b0_0000_0000_0000; int[] opcodesROR = {};
//        modeRTI = 0b0_0000_0000_0000; int[] opcodesRTI = {};
//
//        modeRTS = 0b0_0000_0000_0000; int[] opcodesRTS = {};
//        modeSBC = 0b0_0000_0000_0000; int[] opcodesSBC = {};
//        modeSEC = 0b0_0000_0000_0000; int[] opcodesSEC = {};
//        modeSED = 0b0_0000_0000_0000; int[] opcodesSED = {};
//        modeSEI = 0b0_0000_0000_0000; int[] opcodesSEI = {};
//        modeSTA = 0b0_0000_0000_0000; int[] opcodesSTA = {};
//        modeSTX = 0b0_0000_0000_0000; int[] opcodesSTX = {};
//        modeSTY = 0b0_0000_0000_0000; int[] opcodesSTY = {};
//        modeTAX = 0b0_0000_0000_0000; int[] opcodesTAX = {};
//        modeTAY = 0b0_0000_0000_0000; int[] opcodesTAY = {};
//        modeTSX = 0b0_0000_0000_0000; int[] opcodesTSX = {};
//        modeTXA = 0b0_0000_0000_0000; int[] opcodesTXA = {};
//        modeTXS = 0b0_0000_0000_0000; int[] opcodesTXS = {};
//        modeTYA = 0b0_0000_0000_0000; int[] opcodesTYA = {};

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
        int[] temp = binaryAssembler.get(instruction);
        return temp[(mode + 12) % 13];
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
}


