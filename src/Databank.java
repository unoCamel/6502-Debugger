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
        int[] opcodesADC = {0x71, 0x61, 0xFF, 0x79, 0x7D, 0x6D, 0xFF, 0xFF, 0x75, 0x65, 0x69, 0xFF, 0xFF, 0b1_1011_1001_1100};
        int[] opcodesAND = {0x31, 0x21, 0xFF, 0x39, 0x3D, 0x2D, 0XFF, 0XFF, 0X35, 0X25, 0X29, 0XFF, 0XFF, 0b1_1011_1001_1100};
        int[] opcodesASL = {0xFF, 0xFF, 0xFF, 0xFF, 0x1E, 0x0E, 0XFF, 0XFF, 0X16, 0X06, 0XFF, 0X0A, 0XFF, 0b0_0001_1001_1010};
        int[] opcodesBCC = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0X90, 0b0_0000_0000_0001};
        int[] opcodesBCS = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XB0, 0b0_0000_0000_0001};
        int[] opcodesBEQ = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XF0, 0b0_0000_0000_0001};
        int[] opcodesBIT = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0x2C, 0XFF, 0XFF, 0XFF, 0X24, 0XFF, 0XFF, 0XFF, 0b0_0000_1000_1000};
        int[] opcodesBMI = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0X30, 0b0_0000_0000_0001};
        int[] opcodesBNE = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XD0, 0b0_0000_0000_0001};
        int[] opcodesBPL = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0X10, 0b0_0000_0000_0001};
        int[] opcodesBRK = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0X00, 0b0_0000_0000_0001};
        int[] opcodesBVC = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0X50, 0b0_0000_0000_0001};
        int[] opcodesBVS = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0X70, 0b0_0000_0000_0001};
        int[] opcodesCLC = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0X18, 0b0_0000_0000_0001};
        //TODO
                          /*13    12	11	  10	9	  8	    7	  6     5     4	    3	  2     1   MODE= 12   8    4  1*/
        int[] opcodesCLD = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesCLI = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesCLV = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesCMP = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesCPX = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesCPY = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesDEC = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesDEX = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesDEY = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesEOR = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesINC = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesINX = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesINY = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesJMP = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        //TODO
                          /*13    12	11	  10	9	  8	    7	  6     5     4	    3	  2     1   MODE= 12   8    4  1*/
        int[] opcodesJSR = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesLDA = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesLDX = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesLDY = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesLSR = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesNOP = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesORA = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesPHA = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesPHP = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesPLA = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesPLP = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesROL = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesROR = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesRTI = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        //TODO
                          /*13    12	11	  10	9	  8	    7	  6     5     4	    3	  2     1   MODE= 12   8    4  1*/
        int[] opcodesRTS = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesSBC = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesSEC = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesSED = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesSEI = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesSTA = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesSTX = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesSTY = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesTAX = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesTAY = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesTSX = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesTXA = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesTXS = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};
        int[] opcodesTYA = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0XFF, 0b0_0000_0000_0000};


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


