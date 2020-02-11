public abstract class SystemCall {
    private int id;
    private String name;
    private Argument[] arguments;

    public SystemCall(int id, String name, Argument... arguments) {
        this.id = id;
        this.name = name;
        this.arguments = arguments;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Argument getArgument(int i) {
        if (i < 0 || i >= arguments.length)
            throw new IndexOutOfBoundsException();
        return arguments[i];
    }

    public int getArgumentsCount() {
        return arguments.length;
    }

    public abstract void Call(Argument... arguments);
}
