import org.junit.Assert;
import java.util.Arrays;

public class Memory_UnitTest {

    public Memory_UnitTest(){}

    private static void runSetMemory(){
        Memory Mem = new Memory();
        int[] testInstruction = {0xA9, 0x01, 0x00, 0x02, 0xA9, 0x05, 0x8D, 0x01, 0x02, 0xA9, 0x08, 0x8D, 0x02, 0x02};
        int[] testRange = {0xBC, 0xFF};
        //Setting memory to test with
        //Memory.RAM = new int[0x3F00];
        Memory.setMemory(testInstruction);

        //Reading from index 0x0100
        //System.out.println("Memory at 0x0200 is: " + Memory.read(0x0200));
        //System.out.println("Memory at 0x0201 is: " + Memory.read(0x0201));
        Assert.assertEquals(Memory.read(0x0200), 0xA9);
        Assert.assertEquals(Memory.read(0x020C), 0x02);
        //Writing to memory
        Memory.write(0x020D, 0xBC);
        Memory.write(0x020E, 0xFF);

        Memory.write(0x0000, 0xBC);
        Memory.write(0xFFFF, 0xBC);
        //Reading written values
        Assert.assertEquals(Memory.read(0x020E), 0xFF);
        Assert.assertEquals(Memory.read(0x0000), 0xBC);
        Assert.assertEquals(Memory.read(0xFFFF), 0xBC);
        Assert.assertEquals(Memory.read(0x020D), 0xBC);
        //Assert.assertArrayEquals(testRange, Memory.readRange(0x020D, 0x020E));

    }
    public static void runMemorySuite(){
        System.out.println("Starting Memory Tests");
        runSetMemory();
        System.out.println("Finished Memory Tests");

    }
}
