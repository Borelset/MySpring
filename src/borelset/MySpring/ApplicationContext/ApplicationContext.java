package borelset.MySpring.ApplicationContext;

import borelset.MySpring.IoC.Bean.BeanDefination;
import borelset.MySpring.IoC.Bean.BeanFactory.AutowireCapableBeanFactory;
import borelset.MySpring.IoC.Bean.BeanFactory.BeanFactory;
import borelset.MySpring.IoC.Bean.BeanFactory.BeanPostProcessor;
import borelset.MySpring.IoC.Resource.ResourceLoader;
import borelset.MySpring.IoC.Resource.ResourceReader.XmlResourceReader;

import java.util.List;
import java.util.Map;

public class ApplicationContext implements BeanFactory{
    private AutowireCapableBeanFactory mAutowireCapableBeanFactory;
    private String location;

    public ApplicationContext(String location){
        mAutowireCapableBeanFactory = new AutowireCapableBeanFactory();
        this.location = location;
        refresh();
    }

    public void refresh(){
        XmlResourceReader xmlResourceReader = new XmlResourceReader(new ResourceLoader());
        xmlResourceReader.loadDefination(location);

        for(Map.Entry<String, BeanDefination> entry: xmlResourceReader.getBeansParsed().entrySet()){
            mAutowireCapableBeanFactory.registerBean(entry.getKey(), entry.getValue());
        }
        registerBeanPostProcessors(mAutowireCapableBeanFactory);
    }

    public void registerBeanPostProcessors(AutowireCapableBeanFactory factory){
        List<BeanPostProcessor> beanPostProcessors = mAutowireCapableBeanFactory.getBeansForType(BeanPostProcessor.class);
        for(BeanPostProcessor processor: beanPostProcessors){
            mAutowireCapableBeanFactory.addBeanPostProcessor(processor);
        }
    }

    @Override
    public Object getBean(String name) {
        return mAutowireCapableBeanFactory.getBean(name);
    }
}
