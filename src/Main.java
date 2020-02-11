import java.lang.reflect.InvocationTargetException;

public class Main {
    private Stack<Argument> stack;
    private Core core;

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        stack = new Stack<>();
        core = new Core(stack,
                new SystemCall(0, "Plus", new Argument(Integer.class), new Argument(Integer.class)) {
                    @Override
                    public void Call(Argument... arguments) {
                        int a = (int) arguments[0].getValue();
                        int b = (int) arguments[1].getValue();
                        System.out.println("A + B = " + (a + b));
                    }
                },
                new SystemCall(1, "Minus", new Argument(Integer.class), new Argument(Integer.class)) {
                    @Override
                    public void Call(Argument... arguments) {
                        int a = (int) arguments[0].getValue();
                        int b = (int) arguments[1].getValue();
                        System.out.println("A - B = " + String.format("%d", a - b));
                    }
                },
                new SystemCall(2, "Multiply", new Argument(Integer.class), new Argument(Integer.class)) {
                    @Override
                    public void Call(Argument... arguments) {
                        int a = (int) arguments[0].getValue();
                        int b = (int) arguments[1].getValue();
                        System.out.println("A * B = " + (a * b));
                    }
                },
                new SystemCall(3, "Divide", new Argument(Float.class), new Argument(Float.class)) {
                    @Override
                    public void Call(Argument... arguments) {
                        float a = (float) arguments[0].getValue();
                        float b = (float) arguments[1].getValue();
                        System.out.println("A / B = " + (a / b));
                    }
                },
                new SystemCall(4, "PercentDivide", new Argument(Integer.class), new Argument(Integer.class)) {
                    @Override
                    public void Call(Argument... arguments) {
                        int a = (int) arguments[0].getValue();
                        int b = (int) arguments[1].getValue();
                        System.out.println("A % B = " + (a % b));
                    }
                }
        );

        core.PrintSystemCalls();
        System.out.println();

        stack.push(new Argument(321));
        stack.push(new Argument(123));
        core.Call("Plus");

        stack.push(new Argument(321));
        stack.push(new Argument(123));
        core.Call(1);

        stack.push(new Argument(321));
        stack.push(new Argument(123));
        core.Call("Multiply");

        stack.push(new Argument(321));
        stack.push(new Argument(123));
        core.Call("PercentDivide");

        stack.push(new Argument(654.321f));
        stack.push(new Argument(123.456f));
        core.Call(3);
    }
}
