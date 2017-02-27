import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        System.out.println("Running Test Suite");
        //Import_UnitTest.runImportSuite();
        //Registers_UnitTest.runRegisterSuite();
        Memory_UnitTest.runMemorySuite();
        String content = null;
        Assembly Asm = null;
        Registers.init_Memory();
        try{
            content = new Scanner(new File("src/simpleADCTest.txt")).useDelimiter("\\Z").next();
            Asm = Import.importInstructions(content);
            Memory.setMemory(Asm.assemble());
            System.out.println(Arrays.toString(Asm.getAllInstructions()));
            Memory.instrToString();
            CPU.CPURun();
            System.out.println("\n Register A is: " + Registers.read8(Global.$A));
        } catch(FileNotFoundException e){


        }








    }
}

