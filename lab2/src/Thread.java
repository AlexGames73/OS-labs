public class Thread {
    private String name;

    public Thread(String name) {
        this.name = name;
    }

    public void continueThread(int processTime) {
        System.out.println(String.format("Thread %s is started for %d", name, processTime));

        try {
            java.lang.Thread.sleep(processTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
