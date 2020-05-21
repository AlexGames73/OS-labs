import java.util.ArrayList;
import java.util.List;

public class Directory {
    private String name;
    private Directory parent;
    private List<Directory> childs;

    public Directory(String name) {
        this.name = name;
        childs = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Directory getParent() {
        return parent;
    }

    public void setParent(Directory parent) {
        this.parent = parent;
    }

    public List<Directory> getChilds() {
        return childs;
    }

    public void rename(String name) {
        this.name = name;
    }

    public void pushChild(Directory directory) {
        childs.add(directory);
    }
}
