import java.util.HashMap;
import java.util.Arrays;

public class Import_UnitTest{

	public static void testImport(){
		String test = "STA $A00B\nLDA #$10\nSTA $A004\nLDA #$27";

		String[] instructions = new String[Global.MAX_MEMORY];
		instructions[0] = "STA $A00B";
		instructions[1] = "LDA #$10";
		instructions[2] = "STA $A004";
		instructions[3] = "LDA #$27";

		HashMap<String, Integer> labels = new HashMap<String, Integer>();
		Assembly expected = new Assembly(instructions, labels);
		Assembly actual = Import.importInstructions(test);

		//check if instructions equal each other
		if (!Arrays.equals(expected.getAllInstructions(), actual.getAllInstructions())){
			System.out.println("IMPORT FAILS TEST: Instruction set is not the same.");
		}

		//check if hashmap is empty
		if (!actual.getAllLabels().isEmpty()){
			System.out.println("IMPORT FAILS TEST: HashMap should be empty for this test.");
		}
	}

	public static void runImportSuite(){
		System.out.println("Starting Import Test");
		testImport();
	}
}