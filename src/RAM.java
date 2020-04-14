public class RAM {
    public static final int TOTAL_SIZE = 0x100000; // 1 MiB

    private MemoryPage[] memory;

    public RAM() {
        this.memory = new MemoryPage[TOTAL_SIZE / MemoryPage.SIZE];
        for (int i = 0; i < memory.length; i++) {
            memory[i] = new MemoryPage("page" + i);
        }
    }

    public VirtualMemory save(MemoryPage[] pages) {
        int minFitInd = -1;
        int minFit = TOTAL_SIZE + 1;
        int startFreeInd = -1;
        boolean free = false;
        int curFree = 0;
        for (int i = 0; i < memory.length; i++) {
            if (free && !memory[i].isFree()) {
                if (curFree >= pages.length && curFree < minFit) {
                    minFit = curFree;
                    minFitInd = startFreeInd;
                }
            }
            if (free && memory[i].isFree()) {
                curFree++;
            }
            if (!free && memory[i].isFree()) {
                startFreeInd = i;
                curFree = 0;
            }
            free = memory[i].isFree();
        }
        if (free) {
            if (curFree >= pages.length && curFree < minFit) {
                minFitInd = startFreeInd;
            }
        }
        if (minFitInd == -1) {
            return null;
        }
        else {
            for (int i = 0; i < pages.length; i++) {
                memory[i + startFreeInd] = pages[i];
                memory[i + startFreeInd].occupy();
            }
            VirtualMemory virtualMemory = new VirtualMemory(pages);
            virtualMemory.setIndexRAM(startFreeInd);
            return virtualMemory;
        }
    }

    public MemoryPage[] getMemory() {
        return memory;
    }
}
