public class ProcessScheduler {
    private Process[] processes;
    private Integer countProcesses;

    public ProcessScheduler() {
        countProcesses = 0;
        processes = new Process[countProcesses];
    }

    public void addProcess(Process process) {
        if (countProcesses == processes.length) {
            Process[] buffer = new Process[countProcesses * 2 + 1];
            System.arraycopy(processes, 0, buffer, 0, countProcesses);
            processes = buffer;
        }

        processes[countProcesses] = process;
        countProcesses++;
    }

    public void run() {
        while (true) {
            for (int i = 0; i < countProcesses; i++) {
                switch (processes[i].getPriority()) {
                    case Low:
                        processes[i].continueProcess(100);
                        break;
                    case Middle:
                        processes[i].continueProcess(300);
                        break;
                    case High:
                        processes[i].continueProcess(700);
                        break;
                    case Realtime:
                        processes[i].continueProcess(1500);
                        break;
                }
            }
        }
    }
}
