package borelset.MySpring.AOP.Pointcut;

import org.aopalliance.intercept.MethodInterceptor;

public class PointcutAdvisor {
    private Pointcut mPointcut = new Pointcut();
    private MethodInterceptor interceptor;

    public Pointcut getPointcut() {
        return mPointcut;
    }

    public void setPointcut(Pointcut pointcut) {
        mPointcut = pointcut;
    }

    public MethodInterceptor getInterceptor() {
        return interceptor;
    }

    public void setInterceptor(MethodInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    public void setExpression(String expression){
        mPointcut.setExpression(expression);
    }
}
