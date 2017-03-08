import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

import java.awt.BorderLayout;
import java.awt.EventQueue;

public class Main {

    public static void main(String[] args) {
        System.out.println("Running Test Suite");
        //Import_UnitTest.runImportSuite();
        //Registers_UnitTest.runRegisterSuite();
        //Memory_UnitTest.runMemorySuite();
        String content = null;
        Assembly Asm = null;
        Registers.init_Memory();
        try{
            //content = new Scanner(new File("src/test_files/simpleADCTest.txt")).useDelimiter("\\Z").next();
            //content = new Scanner(new File("src/test_files/register_transfers.txt")).useDelimiter("\\Z").next();
            //content = new Scanner(new File("src/test_files/jumps.txt")).useDelimiter("\\Z").next();
            //content = new Scanner(new File("src/test_files/logical_and.txt")).useDelimiter("\\Z").next();
            //content = new Scanner(new File("src/test_files/logical_or.txt")).useDelimiter("\\Z").next();
            content = new Scanner(new File("src/test_files/logical_eor.txt")).useDelimiter("\\Z").next();


            Asm = Import.importInstructions(content);
            Memory.clean();
            Memory.setMemory(Asm.assemble());
            System.out.println(Arrays.toString(Asm.getAllInstructions()));
            Memory.instrToString();
            System.out.println();
            //CPU.Execute();
        } catch(FileNotFoundException e){


        }

        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DebuggerGUI frame = new DebuggerGUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});




    }
}

