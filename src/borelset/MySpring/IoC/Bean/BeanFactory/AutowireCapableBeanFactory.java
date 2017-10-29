package borelset.MySpring.IoC.Bean.BeanFactory;

import borelset.MySpring.AOP.Pointcut.AutoProxyCreator;
import borelset.MySpring.IoC.Bean.BeanDefination;
import borelset.MySpring.IoC.Bean.BeanReference;
import borelset.MySpring.IoC.Bean.Property.PropertyValue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AutowireCapableBeanFactory implements BeanFactory{
    private Map<String, BeanDefination> mBeanDefinationMap = new ConcurrentHashMap<>();
    private List<BeanPostProcessor> mBeanPostProcessors = new ArrayList<>();
    private List<String> mBeanNames = new ArrayList<>();

    @Override
    public Object getBean(String name){
        Object bean = null;
        try{
            BeanDefination beanDefination = mBeanDefinationMap.get(name);
            //检查是否是单例已经有实例了
            if(beanDefination.getBean() != null){
                return beanDefination.getBean();
            }
            Class beanClass = beanDefination.getBeanClass();
            bean = beanClass.newInstance();

            //单例模式保存实例引用
            if(createBean(beanDefination, bean)){
                beanDefination.setBean(bean);
            }
            bean = initBean(bean, name);
        }catch (Exception e){
            e.printStackTrace();
        }
        return bean;
    }

    private Object initBean(Object bean, String name) throws Exception{
        for(BeanPostProcessor processor: mBeanPostProcessors){
            bean = processor.postProcessBeforeInitialzation(bean, name);
        }

        for(BeanPostProcessor processor: mBeanPostProcessors){
            bean = processor.postProcessAfterInitialzation(bean, name);
        }
        return bean;
    }

    public boolean createBean(BeanDefination beanDefination, Object bean)throws Exception{
        if(bean instanceof AutoProxyCreator){
            ((AutoProxyCreator) bean).setBeanFactory(this);
        }
        boolean result = false;
        for(PropertyValue propertyValue: beanDefination.getProperties().getPropertyValueList()){
            String name = propertyValue.getName();
            Object value = propertyValue.getValue();
            //单例则返回true
            if(name.equals("singleton") && value.equals("true")){
                result = true;
                continue;
            }
            if(value instanceof BeanReference){
                value = getBean(((BeanReference) value).getName());

            }
            try{
                Method method = beanDefination.getBeanClass().getMethod("set" + name.substring(0, 1).toUpperCase() + name.substring(1), value.getClass());
                method.setAccessible(true);
                method.invoke(bean, value);
            }catch (NoSuchMethodException e){
                Field field = beanDefination.getBeanClass().getDeclaredField(name);
                field.setAccessible(true);
                field.set(bean, value);
            }
        }
        return result;
    }

    public void registerBean(String name, BeanDefination beanDefination){
        mBeanDefinationMap.put(name, beanDefination);
        mBeanNames.add(name);
    }

    public List getBeansForType(Class cls){
        List<Object> beanList = new ArrayList<>();
        BeanDefination beanDefination = null;
        for(String name: mBeanNames){
            beanDefination = mBeanDefinationMap.get(name);
            if(cls.isAssignableFrom(beanDefination.getBeanClass())){
                beanList.add(getBean(name));
            }
        }
        return beanList;
    }

    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        mBeanPostProcessors.add(beanPostProcessor);
    }
}
