public class Process {
    private MemoryPage[] memory;
    private MemoryManager memoryManager;
    private int indexExternal;

    public Process(int memoryPagesCount, MemoryManager memoryManager) {
        memory = new MemoryPage[memoryPagesCount];
        for (int i = 0; i < memoryPagesCount; i++) {
            memory[i] = new MemoryPage("page" + i);
        }
        indexExternal = -1;
        this.memoryManager = memoryManager;
        memoryManager.saveProcessToExternal(this);
    }

    public void run() {
        memoryManager.moveProcessToRAM(this);
    }

    public int getIndexExternal() {
        return indexExternal;
    }

    public void setIndexExternal(int indexExternal) {
        this.indexExternal = indexExternal;
    }

    public MemoryPage[] getMemory() {
        return memory;
    }
}
