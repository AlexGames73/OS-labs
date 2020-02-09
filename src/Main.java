import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        SystemCaller.Push("Hello");
        SystemCaller.Push("Alex");
        SystemCaller.Push(230);
        SystemCaller.Call(null, Main.class.getMethod("Method", int.class, String.class));
        SystemCaller.Call(null, Main.class.getMethod("Method", int.class, String.class));
    }

    public static int Method(int a, String b) {
        System.out.println("Method " + a + " " + b);
        return 100;
    }
}
