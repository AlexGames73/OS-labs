import java.time.Instant;

public class MemoryPage {
    public static final int SIZE = 0x1000; // 4 KiB

    private boolean free;
    private boolean changed;
    private long time;
    private String data;
    private int indExternal;

    public MemoryPage(String data) {
        this.data = data;
        free = true;
        changed = false;
    }

    public void occupy() {
        free = false;
        time = Instant.now().toEpochMilli();
    }

    public void free() {
        free = true;
        changed = false;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
        changed = true;
    }

    public boolean isFree() {
        return free;
    }

    public boolean isChanged() {
        return changed;
    }

    public long getTime() {
        return time;
    }

    public int getIndExternal() {
        return indExternal;
    }

    public void setIndExternal(int indExternal) {
        this.indExternal = indExternal;
    }
}
