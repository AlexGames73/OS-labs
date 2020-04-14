public class ExternalMemory {
    public static final int TOTAL_SIZE = 0x40000000; // 1 GiB

    private MemoryPage[] memory;

    public ExternalMemory() {
        this.memory = new MemoryPage[TOTAL_SIZE / MemoryPage.SIZE];
        for (int i = 0; i < TOTAL_SIZE / MemoryPage.SIZE; i++) {
            this.memory[i] = new MemoryPage("page" + i);
            this.memory[i].setIndExternal(i);
        }
    }

    public int save(MemoryPage[] pages) {
        int minFitInd = -1;
        int minFit = TOTAL_SIZE + 1;
        boolean free = true;
        int curFree = 0;
        for (int i = 0; i < memory.length; i++) {
            if (free && !memory[i].isFree()) {
                if (curFree >= pages.length && curFree < minFit) {
                    minFit = curFree;
                    minFitInd = i - 1;
                }
                curFree = 0;
            }
            if (free && memory[i].isFree()) {
                curFree++;
            }
            free = memory[i].isFree();
        }
        if (minFitInd != -1) {
            for (int i = 0; i < pages.length; i++) {
                pages[i].setIndExternal(i + memory[i].getIndExternal());
            }
        }
        return minFitInd;
    }

    public MemoryPage[] getPages(int ind, int size) {
        MemoryPage[] result = new MemoryPage[size];
        System.arraycopy(memory, ind, result, 0, size);
        return result;
    }
}
