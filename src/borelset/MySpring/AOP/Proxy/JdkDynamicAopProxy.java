package borelset.MySpring.AOP.Proxy;

import borelset.MySpring.AOP.Proxy.ProxyUtil.AdviseSupport;
import borelset.MySpring.AOP.Proxy.ProxyUtil.ReflectiveMethodInvocation;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkDynamicAopProxy extends AbstractAopProxy implements InvocationHandler {

    public JdkDynamicAopProxy(AdviseSupport adviseSupport) {
        super(adviseSupport);
    }

    @Override
    public Object getProxy(){
        Object result = Proxy.newProxyInstance(getClass().getClassLoader(), mAdviseSupport.getTargetSource().getTargetIntefaces(), this);
        return result;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MethodInterceptor methodInterceptor = mAdviseSupport.getMethodInterceptor();
        if(mAdviseSupport != null &&
                mAdviseSupport.getMethodMatcher().match(method, mAdviseSupport.getTargetSource().getTarget().getClass()))
            return methodInterceptor.invoke(new ReflectiveMethodInvocation(mAdviseSupport.getTargetSource().getTarget(), method, args));
        else{
            return method.invoke(mAdviseSupport.getTargetSource().getTarget(), args);
        }
    }
}
