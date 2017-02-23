import org.junit.Assert;

public class Registers_UnitTest {
    Registers Reg = new Registers();
    public Registers_UnitTest(){
    }

    public static void testReadWrite(){
        Registers.write8(Global.$A, 0x10);
        Assert.assertEquals(0x10, Registers.read8(Global.$A));
        Registers.write16(Global.$PC, 0xFFFF);
        Assert.assertEquals(0xFFFF, Registers.read16(Global.$PC));
        Registers.write16(Global.$SP, 0x0);
        Assert.assertEquals(0x0, Registers.read16(Global.$SP));
    }

    public static void testPFlags(){
        System.out.println("value: " + Integer.toBinaryString(Registers.read8(Global.$P)));
        Assert.assertTrue(Registers.isCarry() == false);
        Assert.assertTrue(Registers.isNegative() == false);
        Assert.assertTrue(Registers.isZero() == false);
        Registers.setCarry();
        Registers.setNegative();
        Registers.setDecimal();
        System.out.println("value: " + Integer.toBinaryString(Registers.read8(Global.$P)));
        Assert.assertTrue(Registers.isCarry() == true);
        Assert.assertTrue(Registers.isZero() == false);
        Assert.assertTrue(Registers.isNegative() == true);
        Assert.assertTrue(Registers.isDecimal() == true);
        Registers.resetNegative();
        System.out.println("value: " + Integer.toBinaryString(Registers.read8(Global.$P)));
        Assert.assertTrue(Registers.isCarry() == true);
        Assert.assertTrue(Registers.isNegative() == false);
        Assert.assertTrue(Registers.isDecimal() == true);
    }

    public static void runRegisterSuite(){
            System.out.println("Starting Register Tests");
            testReadWrite();
            testPFlags();
    }

}
