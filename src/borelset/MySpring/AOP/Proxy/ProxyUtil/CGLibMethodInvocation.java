package borelset.MySpring.AOP.Proxy.ProxyUtil;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLibMethodInvocation extends ReflectiveMethodInvocation {
    private MethodProxy mMethodProxy;

    public CGLibMethodInvocation(Object target, Method method, Object[] args, MethodProxy methodProxy) {
        super(target, method, args);
        mMethodProxy = methodProxy;
    }

    @Override
    public Object proceed() throws Throwable {
        return mMethodProxy.invoke(this.target, this.args);
    }
}
