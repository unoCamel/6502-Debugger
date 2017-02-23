public class Main {

    public static void main(String[] args) {
        System.out.println("Running Test Suite");
        Import_UnitTest.runImportSuite();
        Registers_UnitTest.runRegisterSuite();
        Memory_UnitTest.runMemorySuite();
    }

}