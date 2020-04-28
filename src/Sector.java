public class Sector {
    private Integer size;
    private SectorType type;
    private SectorState state;

    public Sector(Integer size) {
        this.size = size;
        type = SectorType.EMPTY;
        state = SectorState.STAYING;
    }

    public void move() {
        state = SectorState.MOVING;
    }

    public void create(SectorType type) {
        state = SectorState.CREATING;
        this.type = type;
    }

    public void copy() {
        state = SectorState.COPYING;
    }

    public void remove() {
        state = SectorState.REMOVING;
        type = SectorType.EMPTY;
    }

    public void stay() {
        state = SectorState.STAYING;
    }

    public Integer getSize() {
        return size;
    }

    public SectorType getType() {
        return type;
    }

    public SectorState getState() {
        return state;
    }

    public enum SectorState {
        CREATING,
        COPYING,
        MOVING,
        REMOVING,
        STAYING
    }

    public enum SectorType {
        FILE,
        FOLDER,
        EMPTY
    }
}
