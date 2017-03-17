import java.util.HashMap;
import org.junit.Assert;


public class Assembly_UnitTest {
	public static void testAssemle(){
		
		
		Assembly test = Import.importInstructions(""
				+ "LSR A\n" 		//Accumulator
				+ "LABELA: RTI \n" 	//Implicit
				+ "ADC #$44\n" 		//Immediate
				+ "AND $44\n"		//ZeroPage 
				+ "ASL 44,X\n"		//ZeroPageX 
				+ "STX $44,Y\n"		//ZeroPageY
				+ "BPL LABELA\n"	//Branch
				+ "INC 4400\n"		//Absolute
				+ "LDA 4400,X\n"	//AbsoluteX
				+ "SBC 4400,Y\n"	//AbsoluteY
				+ "JMP (LABELA)\n"	//Indirect
				+ "EOR (44,X)\n"	//IndirectX
				+ "CMP ($44),Y\n"	//IndirectY
				);	//Indirect
		
		
		Integer[] expected = {
				0x4A,				//Accumulator
				0x40,				//Implicit
				0x69, 0x44,			//Immediate 
				0x25, 0x44,			//ZeroPage 
				0x16, 0x44,			//ZeroPageX 
				0x96, 0x44, 		//ZeroPageY
				0x10, 0x1,			//Branch
				0xEE, 0x00, 0x44,	//Absolute
				0xBD, 0x00, 0x44,	//AbsoluteX
				0xF9, 0x00, 0x44,	//AbsoluteY
				0x6C, 0x1,			//Indirect
				0x41, 0x44,			//IndirectX
				0xD1, 0x44,
				};			//IndirectY

		 Integer[] actual = test.assemble();
		 System.out.println("expected length: " + expected.length);
		 for (int n: expected){
			 System.out.print(Integer.toHexString(n));
		 }
		 System.out.println();
		 
		 System.out.println("actual length: " + actual.length);
		 for (int n: actual){
			 System.out.print(Integer.toHexString(n));
		 }
		 System.out.println();
		 
		Assert.assertArrayEquals(expected, actual);
	}

	public static void runImportSuite(){
		System.out.println("Starting Assembly Test");
		testAssemle();
	}
}
