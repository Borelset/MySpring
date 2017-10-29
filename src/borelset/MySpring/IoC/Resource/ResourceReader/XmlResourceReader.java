package borelset.MySpring.IoC.Resource.ResourceReader;

import borelset.MySpring.IoC.Bean.BeanDefination;
import borelset.MySpring.IoC.Bean.BeanReference;
import borelset.MySpring.IoC.Bean.Property.PropertyValue;
import borelset.MySpring.IoC.Resource.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class XmlResourceReader implements ResourceReader {
    private Map<String, BeanDefination> beansParsed = new ConcurrentHashMap<>();
    private ResourceLoader mResourceLoader;

    public XmlResourceReader(ResourceLoader resourceLoader) {
        mResourceLoader = resourceLoader;
    }

    @Override
    public void loadDefination(String location){
        InputStream inputStream = mResourceLoader.getResource(location).getInputStream();
        doLoadDefination(inputStream);
    }

    private void doLoadDefination(InputStream inputStream){
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);
            parseDocument(document);
            inputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void parseDocument(Document document){
        Element root = document.getDocumentElement();
        NodeList nodeList = root.getChildNodes();
        for(int i=0; i<nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            if(node instanceof Element){
                Element element = (Element)node;
                processElement(element);
            }
        }
    }

    private void processElement(Element element){
        String name = element.getAttribute("id");
        String className = element.getAttribute("class");
        BeanDefination beanDefination = new BeanDefination();
        processProperty(element, beanDefination);
        beanDefination.setBeanClassName(className);
        beansParsed.put(name, beanDefination);
    }

    private void processProperty(Element element, BeanDefination beanDefination){
        NodeList nodeList = element.getElementsByTagName("property");
        for(int i=0; i<nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            if(node instanceof Element){
                Element elementProperty = (Element)node;
                String name = elementProperty.getAttribute("name");
                String value = elementProperty.getAttribute("value");
                if(value != null && value.length()>0){
                    beanDefination.getProperties().addPropertyValue(new PropertyValue(name, value));
                }else{
                    String ref = elementProperty.getAttribute("ref");
                    if(ref == null || ref.length() == 0){
                        throw new IllegalArgumentException("BeanDefination Loading: There must be a value or a ref in a property");
                    }
                    BeanReference beanReference = new BeanReference();
                    beanReference.setName(ref);
                    beanDefination.getProperties().addPropertyValue(new PropertyValue(name, beanReference));
                }
            }
        }
    }

    public Map<String, BeanDefination> getBeansParsed() {
        return beansParsed;
    }
}
