package borelset.MySpring.AOP.Pointcut;

import borelset.MySpring.AOP.Proxy.CGLibAopProxy;
import borelset.MySpring.AOP.Proxy.ProxyUtil.AdviseSupport;
import borelset.MySpring.AOP.Proxy.JdkDynamicAopProxy;
import borelset.MySpring.AOP.Proxy.ProxyUtil.TargetSource;
import borelset.MySpring.IoC.Bean.BeanFactory.AutowireCapableBeanFactory;
import borelset.MySpring.IoC.Bean.BeanFactory.BeanFactory;
import borelset.MySpring.IoC.Bean.BeanFactory.BeanPostProcessor;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.List;

public class AutoProxyCreator implements BeanPostProcessor {
    private AutowireCapableBeanFactory mBeanFactory;

    @Override
    public Object postProcessBeforeInitialzation(Object bean, String beanName) throws Exception {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialzation(Object bean, String beanName) throws Exception {
        if(bean instanceof PointcutAdvisor){
            return bean;
        }
        if(bean instanceof MethodInterceptor){
            return bean;
        }
        List<PointcutAdvisor> pointcutAdvisors = mBeanFactory.getBeansForType(PointcutAdvisor.class);
        for(PointcutAdvisor pointcutAdvisor: pointcutAdvisors){
            if(pointcutAdvisor.getPointcut().getClassFilter().match(bean.getClass())){
                AdviseSupport adviseSupport = new AdviseSupport();
                adviseSupport.setMethodInterceptor(pointcutAdvisor.getInterceptor());
                adviseSupport.setMethodMatcher(pointcutAdvisor.getPointcut().getMethodMatcher());
                TargetSource targetSource = new TargetSource(bean.getClass(), bean.getClass().getInterfaces(), bean);
                adviseSupport.setTargetSource(targetSource);

                return new CGLibAopProxy(adviseSupport).getProxy();
            }
        }
        return bean;
    }

    public BeanFactory getBeanFactory() {
        return mBeanFactory;
    }

    public void setBeanFactory(AutowireCapableBeanFactory beanFactory) {
        mBeanFactory = beanFactory;
    }
}
