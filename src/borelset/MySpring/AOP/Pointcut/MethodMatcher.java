package borelset.MySpring.AOP.Pointcut;

import java.lang.reflect.Method;

public interface MethodMatcher {
    public boolean match(Method method, Class cls);
}
