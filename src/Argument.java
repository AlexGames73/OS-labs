public class Argument {
    private Class<?> type;
    private Object value;

    public Argument(Class<?> type) {
        this.type = type;
    }

    public Argument(Object value) {
        this.type = value.getClass();
        this.value = value;
    }

    public Class<?> getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }
}
