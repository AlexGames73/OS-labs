import java.lang.reflect.Array;

public class Stack<T> {
    private Object[] values;
    private int count;

    public Stack() {
        values = new Object[2];
        count = 0;
    }

    public void push(T elem) {
        values[count] = elem;
        count++;
    }

    public T pop() {
        if (isEmpty())
            throw new IndexOutOfBoundsException();

        T obj = (T)values[count - 1];
        count--;
        return obj;
    }

    private boolean isEmpty() {
        return count == 0;
    }
}
