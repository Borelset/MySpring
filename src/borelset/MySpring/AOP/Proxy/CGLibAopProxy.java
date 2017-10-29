package borelset.MySpring.AOP.Proxy;

import borelset.MySpring.AOP.Proxy.AbstractAopProxy;
import borelset.MySpring.AOP.Proxy.ProxyUtil.AdviseSupport;
import borelset.MySpring.AOP.Proxy.ProxyUtil.CGLibInterceptor;
import net.sf.cglib.proxy.Enhancer;

public class CGLibAopProxy extends AbstractAopProxy {

    public CGLibAopProxy(AdviseSupport adviseSupport) {
        super(adviseSupport);
    }

    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(mAdviseSupport.getTargetSource().getTargetClass());
        enhancer.setInterfaces(mAdviseSupport.getTargetSource().getTargetIntefaces());
        enhancer.setCallback(new CGLibInterceptor(mAdviseSupport));
        Object result = enhancer.create();
        return result;
    }
}
