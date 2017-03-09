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
        Memory.clean();

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

