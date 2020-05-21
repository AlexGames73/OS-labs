import java.util.ArrayList;
import java.util.List;

public class File {
    private String name;
    private Directory directory;
    private Sector head;

    public File(String name, Directory directory) {
        this.name = name;
        this.directory = directory;
    }

    public Sector getHead() {
        return head;
    }

    public void setHead(Sector head) {
        this.head = head;
    }

    public String getName() {
        return name;
    }

    public Directory getDirectory() {
        return directory;
    }

    public String getContent() {
        StringBuilder res = new StringBuilder();
        Sector current = head;
        while (current != null) {
            res.append(current.getContent());
            current = current.getNextSector();
        }
        return res.toString();
    }
}
