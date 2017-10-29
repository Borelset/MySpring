package borelset.MySpring.AOP.Proxy;

import borelset.MySpring.AOP.Proxy.ProxyUtil.AdviseSupport;

public abstract class AbstractAopProxy {
    protected AdviseSupport mAdviseSupport;

    public AbstractAopProxy(AdviseSupport adviseSupport) {
        mAdviseSupport = adviseSupport;
    }

    public abstract Object getProxy();
}
