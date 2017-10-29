package Test.Bean;

public class TestClass1 implements TestInterface1{
    private String testField1;

    @Override
    public void boomboombow(){
        System.out.println("Boom!Boom!Bow! " + testField1);
    }

    @Override
    public void gonnagetget() {
        System.out.println("Gonna get get! " + testField1);
    }
}
