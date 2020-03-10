import java.util.ArrayList;
import java.util.List;

public class ProcessScheduler {
    private List<Process> processes;
    private List<Thread> threads;

    public ProcessScheduler() {
        processes = new ArrayList<>();
        threads = new ArrayList<>();
    }

    public void addProcess(Process process) {
        processes.add(process);
        threads.addAll(process.getThreads());
    }

    public void run() {
        while (true) {
            for (Process process : processes) {
                process.activate();
                int processTime = process.getPriority().getTime();
                process.continueProcess(processTime);
                int processTimeThread = Math.min(processTime, 20);
                int countCalls = processTime / processTimeThread;
                for (int i = 0; i < countCalls; i++) {
                    threads.get(process.getPointer() % threads.size()).continueThread(processTimeThread);
                    process.setPointer((process.getPointer() + 1) % threads.size());
                }
                process.freeze();
            }
        }
    }
}
