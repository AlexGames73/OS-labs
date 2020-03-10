import java.util.ArrayList;
import java.util.List;

public class Process {
    private List<Thread> threads;
    private Integer pointer;
    private String name;
    private PriorityType priority;
    private Boolean isActive;

    public Process(String name, PriorityType priority) {
        pointer = 0;
        threads = new ArrayList<>();

        this.name = name;
        this.priority = priority;
    }

    public void addThread(Thread thread) {
        threads.add(thread);
    }

    public void activate() {
        isActive = true;
    }

    public void continueProcess(int processTime) {
        System.out.println(String.format("Process %s is started for %d", name, processTime));
    }

    public void freeze() {
        isActive = false;
    }

    public List<Thread> getThreads() {
        return threads;
    }

    public Boolean getActive() {
        return isActive;
    }

    public Integer getPointer() {
        return pointer;
    }

    public void setPointer(Integer pointer) {
        this.pointer = pointer;
    }

    public PriorityType getPriority() {
        return priority;
    }
}
