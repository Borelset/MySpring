package borelset.MySpring.IoC.Bean.BeanFactory;

public interface BeanPostProcessor {
    public Object postProcessBeforeInitialzation(Object bean, String beanName) throws Exception;
    public Object postProcessAfterInitialzation(Object bean, String beanName) throws Exception;
}
