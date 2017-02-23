import org.junit.Assert;

public class Registers_UnitTest {
    Registers Reg = new Registers();
    public Registers_UnitTest(){
    }

    public static void testReadWrite(){
        Registers.write8(Global.$A, 0x10);
        Assert.assertEquals(0x10, Registers.read8(Global.$A));
    }

    public static void runRegisterSuite(){
            System.out.println("Starting Register Tests");
            testReadWrite();
    }

}
