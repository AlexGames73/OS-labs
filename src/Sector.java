public class Sector {
    public static final int SECTOR_SIZE = 10;

    private SectorType type;
    private SectorState state;
    private String content;
    private Sector nextSector;

    public Sector(String content, SectorType sectorType, Sector nextSector) {
        this.content = content;
        this.nextSector = nextSector;
        this.type = sectorType;
        this.state = SectorState.STAYING;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Sector getNextSector() {
        return nextSector;
    }

    public void setNextSector(Sector nextSector) {
        this.nextSector = nextSector;
    }

    public void move() {
        state = SectorState.MOVING;
        if (nextSector != null) {
            nextSector.move();
        }
    }

    public void create(SectorType type) {
        state = SectorState.CREATING;
        this.type = type;
        if (nextSector != null) {
            nextSector.create(type);
        }
    }

    public void copy() {
        state = SectorState.COPYING;
        if (nextSector != null) {
            nextSector.copy();
        }
    }

    public void remove() {
        if (nextSector != null) {
            nextSector.remove();
            nextSector = null;
        }
        content = "";
        state = SectorState.REMOVING;
        type = SectorType.EMPTY;
    }

    public void select() {
        state = SectorState.SELECTING;
        if (nextSector != null) {
            nextSector.select();
        }
    }

    public void stay() {
        state = SectorState.STAYING;
        if (nextSector != null) {
            nextSector.stay();
        }
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
        SELECTING,
        STAYING
    }

    public enum SectorType {
        FILE,
        DIRECTORY,
        EMPTY
    }
}
