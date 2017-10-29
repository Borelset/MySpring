package borelset.MySpring.IoC.Bean;

import borelset.MySpring.IoC.Bean.Property.Properties;

public class BeanDefination {
    private Object bean;
    private Class beanClass;
    private String beanClassName;
    private Properties mProperties = new Properties();

    public Properties getProperties() {
        return mProperties;
    }

    public void setProperties(Properties properties) {
        mProperties = properties;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        try{
            beanClass = Class.forName(beanClassName);
            this.beanClassName = beanClassName;
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
