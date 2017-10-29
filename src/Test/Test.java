package Test;

import Test.Bean.TestClass1;
import Test.Bean.TestInterface1;
import borelset.MySpring.ApplicationContext.ApplicationContext;

public class Test {
    public static void main(String[] args) throws Exception{

        /*
        ApplicationContext applicationContext = new ApplicationContext("Test/Defination.xml");

        TestClass1 testClass1 = (TestClass1)applicationContext.getBean("hello");
        testClass1.boomboombow();

        TargetSource targetSource = new TargetSource(TestClass1.class.getInterfaces(), testClass1);
        TimeInterceptor timeIntercepter = new TimeInterceptor();
        AdviseSupport adviseSupport = new AdviseSupport();
        adviseSupport.setTargetSource(targetSource);
        adviseSupport.setMethodInterceptor(timeIntercepter);

        JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(adviseSupport);
        TestInterface1 afsa = (TestInterface1) jdkDynamicAopProxy.getProxy();
        afsa.boomboombow();
        */

        ApplicationContext applicationContext = new ApplicationContext("Test/Defination.xml");
        TestClass1 afsa = (TestClass1) applicationContext.getBean("hello");
        afsa.boomboombow();
        afsa.gonnagetget();
    }
}
