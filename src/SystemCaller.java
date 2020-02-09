import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Stack;

public class SystemCaller {
    private static Stack<Object> objectStack = new Stack<>();

    public static void Push(Object obj) {
        objectStack.push(obj);
    }

    public static Object Pop() {
        return objectStack.pop();
    }

    public static void Call(Object obj, Method method) throws InvocationTargetException, IllegalAccessException {
        int countParams = method.getParameterCount();
        Object[] objects = new Object[countParams];
        for (int i = 0; i < countParams; i++) {
            objects[i] = Pop();
        }
        obj = method.invoke(obj, objects);
        if (obj != null)
            Push(obj);
    }
}
