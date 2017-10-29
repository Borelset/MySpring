package borelset.MySpring.AOP.Pointcut;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

public class Pointcut implements MethodMatcher, ClassFilter{
    private String expression;
    private String classExpression;
    private String methodExpression;

    public Pointcut(){
        expression = "";
    }

    public Pointcut(String expression) {
        this.expression = expression;
        parseExpression();
        preprocess();
    }

    @Override
    public boolean match(Class cls) {
        return Pattern.matches(classExpression, cls.getName());
    }

    @Override
    public boolean match(Method method, Class cls) {
        String content = cls.getName() + "." + method.getName()+ "()";
        return Pattern.matches(expression, content);
    }

    public MethodMatcher getMethodMatcher(){
        return this;
    }

    public ClassFilter getClassFilter(){
        return this;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
        parseExpression();
        preprocess();
    }

    private void parseExpression(){
        int end = expression.lastIndexOf("()");
        int start = expression.lastIndexOf(".");
        classExpression = expression.substring(0, start);
        methodExpression = expression.substring(start+1, end);
    }


    private void preprocess(){
        expression = expression.replaceAll("\\.", "\\\\\\.")
                .replaceAll("\\*", ".*")
                .replaceAll("\\(", "\\\\(")
                .replaceAll("\\)", "\\\\)");
        classExpression = classExpression.replaceAll("\\.", "\\\\\\.")
                .replaceAll("\\*", ".*");
        methodExpression = methodExpression.replaceAll("\\*", ".*");
    }
}
