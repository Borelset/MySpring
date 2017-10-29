package borelset.MySpring.AOP.Proxy.ProxyUtil;

public class TargetSource {
    private Class targetClass;
    private Class[] targetIntefaces;
    private Object target;

    public TargetSource(Class targetClass, Class[] targetIntefaces, Object target) {
        this.targetClass = targetClass;
        this.targetIntefaces = targetIntefaces;
        this.target = target;
    }

    public Class getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class targetClass) {
        this.targetClass = targetClass;
    }

    public Class[] getTargetIntefaces() {
        return targetIntefaces;
    }

    public void setTargetIntefaces(Class[] targetIntefaces) {
        this.targetIntefaces = targetIntefaces;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }
}
