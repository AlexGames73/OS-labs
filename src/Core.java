import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Optional;

public class Core {
    private SystemCall[] systemCalls;
    private Stack<Argument> arguments;

    public Core(Stack<Argument> arguments, SystemCall... systemCalls) {
        this.arguments = arguments;
        this.systemCalls = systemCalls;
    }

    public void PrintSystemCalls() {
        for (SystemCall systemCall : systemCalls) {
            String res = "{" + systemCall.getId() + "}" + systemCall.getName() + "(";
            String[] args = new String[systemCall.getArgumentsCount()];
            for (int i = 0; i < systemCall.getArgumentsCount(); i++) {
                args[i] = systemCall.getArgument(i).getType().getName();
            }
            res += String.join(", ", args) + ")";
            System.out.println(res);
        }
    }

    private void Call(SystemCall systemCall) {
        if (systemCall == null) {
            return;
        }

        Argument[] arguments = new Argument[systemCall.getArgumentsCount()];
        for (int i = 0; i < systemCall.getArgumentsCount(); i++) {
            arguments[i] = this.arguments.pop();
            if (arguments[i] == null) {
                throw new IndexOutOfBoundsException();
            }
            if (arguments[i].getType() != systemCall.getArgument(i).getType()) {
                throw new IllegalArgumentException();
            }
        }

        systemCall.Call(arguments);
    }

    public void Call(int id) {
        Optional<SystemCall> res = Arrays.stream(systemCalls).filter(x -> x.getId() == id).findAny();
        Call(res.orElse(null));
    }

    public void Call(String name) {
        Optional<SystemCall> res = Arrays.stream(systemCalls).filter(x -> x.getName().equals(name)).findAny();
        Call(res.orElse(null));
    }
}
