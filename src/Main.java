public class Main {

    public static void main(String[] args) {
        System.out.println("Running Test Suite");
        Registers_UnitTest RegistersUT = new Registers_UnitTest();
        Registers_UnitTest.runRegisterSuite();
        Memory_UnitTest.runMemorySuite();

    }

}