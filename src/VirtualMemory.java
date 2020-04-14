public class VirtualMemory {
    private int indexRAM;
    private MemoryPage[] pages;

    public VirtualMemory(MemoryPage[] pages) {
        this.pages = pages;
    }

    public int getIndexRAM() {
        return indexRAM;
    }

    public void setIndexRAM(int indexRAM) {
        this.indexRAM = indexRAM;
    }

    public MemoryPage[] getPages() {
        return pages;
    }
}
