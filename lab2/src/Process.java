public class Process {
    private Thread[] threads;
    private Integer countThreads;
    private String name;
    private PriorityType priority;

    public Process(String name, PriorityType priority) {
        countThreads = 0;
        threads = new Thread[countThreads];

        this.name = name;
        this.priority = priority;
    }

    public void addThread(Thread thread) {
        if (countThreads == threads.length) {
            Thread[] buffer = new Thread[countThreads * 2 + 1];
            System.arraycopy(threads, 0, buffer, 0, countThreads);
            threads = buffer;
        }

        threads[countThreads] = thread;
        countThreads++;
    }

    public void continueProcess(int processTime) {
        System.out.println(String.format("Process %s is started for %d", name, processTime));
        int processTimeEveryThread = Math.min((processTime / countThreads) / 5 * 5, 70);
        int countCalls = processTime / processTimeEveryThread;
        for (int i = 0; i < countCalls; i++) {
            threads[i % countThreads].continueThread(processTimeEveryThread);
        }
    }

    public PriorityType getPriority() {
        return priority;
    }
}
