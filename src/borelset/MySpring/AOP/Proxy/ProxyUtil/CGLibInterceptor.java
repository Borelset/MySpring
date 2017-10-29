package borelset.MySpring.AOP.Proxy.ProxyUtil;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLibInterceptor implements MethodInterceptor {
    private AdviseSupport mAdviseSupport;
    private org.aopalliance.intercept.MethodInterceptor packedMethodInterceptor;

    public CGLibInterceptor(AdviseSupport adviseSupport) {
        mAdviseSupport = adviseSupport;
        packedMethodInterceptor = mAdviseSupport.getMethodInterceptor();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if(mAdviseSupport != null &&
                mAdviseSupport.getMethodMatcher().match(method, mAdviseSupport.getTargetSource().getTarget().getClass()))
            return packedMethodInterceptor.invoke(new CGLibMethodInvocation(mAdviseSupport.getTargetSource().getTarget(), method, objects, methodProxy));
        else {
            return new CGLibMethodInvocation(mAdviseSupport.getTargetSource().getTarget(), method, objects, methodProxy).proceed();
        }
    }


}
