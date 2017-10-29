package borelset.MySpring.AOP.Proxy.ProxyUtil;

import borelset.MySpring.AOP.Pointcut.MethodMatcher;
import org.aopalliance.intercept.MethodInterceptor;

public class AdviseSupport {
    private TargetSource mTargetSource;
    private MethodInterceptor mMethodInterceptor;
    private MethodMatcher mMethodMatcher;

    public TargetSource getTargetSource() {
        return mTargetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        mTargetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return mMethodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        mMethodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return mMethodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        mMethodMatcher = methodMatcher;
    }
}
