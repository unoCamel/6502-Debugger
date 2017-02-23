import org.junit.Assert;
import java.util.HashMap;

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

		Assert.assertEquals(expected, actual);
	}

	public static void runImportSuite(){
		System.out.println("Starting Import Test");
		testImport();
	}
}