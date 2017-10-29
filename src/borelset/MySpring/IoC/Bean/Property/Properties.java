package borelset.MySpring.IoC.Bean.Property;

import java.util.ArrayList;
import java.util.List;

public class Properties {
    private List<PropertyValue> mPropertyValueList = new ArrayList<>();

    public List<PropertyValue> getPropertyValueList() {
        return mPropertyValueList;
    }

    public void setPropertyValueList(List<PropertyValue> propertyValueList) {
        mPropertyValueList = propertyValueList;
    }

    public void addPropertyValue(PropertyValue propertyValue){
        mPropertyValueList.add(propertyValue);
    }
}
