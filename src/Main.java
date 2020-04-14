import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        ExternalMemory externalMemory = new ExternalMemory();
        RAM ram = new RAM();

        MemoryManager memoryManager = new MemoryManager(externalMemory, ram);

        Process process1 = new Process(100, memoryManager);
        Process process2 = new Process(100, memoryManager);
        Process process3 = new Process(100, memoryManager);
        Process process4 = new Process(50, memoryManager);
        Process process5 = new Process(50, memoryManager);

        process1.run();
        process2.run();
        process3.run();
        process4.run();
        process5.run();
    }
}
