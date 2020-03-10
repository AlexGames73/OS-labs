public class Main {
    public static void main(String[] args) {
        new Main();
    }

    private ProcessScheduler scheduler;

    private Main() {
        scheduler = new ProcessScheduler();

        Process process1 = new Process("process1.exe", PriorityType.Low);
        Process process2 = new Process("process2.exe", PriorityType.Middle);
        Process process3 = new Process("process3.exe", PriorityType.High);
        Process process4 = new Process("process4.exe", PriorityType.Realtime);

        process1.addThread(new Thread("thread1"));
        process1.addThread(new Thread("thread2"));
        process1.addThread(new Thread("thread3"));

        process2.addThread(new Thread("thread1"));
        process2.addThread(new Thread("thread2"));

        process3.addThread(new Thread("thread1"));
        process3.addThread(new Thread("thread2"));
        process3.addThread(new Thread("thread3"));

        process4.addThread(new Thread("thread1"));
        process4.addThread(new Thread("thread2"));
        process4.addThread(new Thread("thread3"));
        process4.addThread(new Thread("thread4"));

        scheduler.addProcess(process1);
        scheduler.addProcess(process2);
        scheduler.addProcess(process3);
        scheduler.addProcess(process4);

        scheduler.run();
    }
}
