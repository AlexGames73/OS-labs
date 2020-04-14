import java.time.Instant;

public class MemoryManager {
    private ExternalMemory externalMemory;
    private RAM ram;

    public MemoryManager(ExternalMemory externalMemory, RAM ram) {
        this.externalMemory = externalMemory;
        this.ram = ram;
    }

    public void saveProcessToExternal(Process process) {
        process.setIndexExternal(externalMemory.save(process.getMemory()));
        System.out.println("process saved to external memory");
    }

    public void moveProcessToRAM(Process process) {
        VirtualMemory virtualMemory = ram.save(process.getMemory());
        while (virtualMemory == null) {
            swappingPagesLRU();
            virtualMemory = ram.save(process.getMemory());
        }
        System.out.println("process moved to RAM");
    }

    public void swappingPagesLRU() {
        long mx = Long.MIN_VALUE;
        MemoryPage mxPage = null;
        for (var page : ram.getMemory()) {
            if (!page.isFree() && mx < Instant.now().toEpochMilli() - page.getTime()) {
                mx = Instant.now().toEpochMilli() - page.getTime();
                mxPage = page;
            }
        }
        assert mxPage != null;
        if (mxPage.isChanged()) {
            externalMemory.getPages(mxPage.getIndExternal(), 1)[0].setData(mxPage.getData());
        }
        mxPage.free();
        System.out.println("iteration of LRU swapping");
    }
}
