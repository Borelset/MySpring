package Test.Interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class TimeInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = methodInvocation.proceed();
        long end = System.currentTimeMillis();
        System.out.println("Duration: " + (end-start) + "ms");
        return result;
    }
}
