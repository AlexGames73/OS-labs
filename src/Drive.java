public class Drive {
    private Integer size;
    private Integer sectorSize;

    private Sector[][] sectors;

    public Drive(Integer size) throws Exception {
        if (size < 2400)
            throw new Exception("Диск не может быть таким маленьким");
        this.size = size;
        sectorSize = size / 2400 + (size % 2400 > 0 ? 1 : 0);
        sectors = new Sector[20][];
        for (int i = 0; i < 20; i++) {
            sectors[i] = new Sector[120];
            for (int j = 0; j < 120; j++) {
                sectors[i][j] = new Sector(sectorSize);
            }
        }
    }

    public void createFile(Integer pointer) {
        int sectorId = pointer / sectorSize;
        sectors[sectorId / 120][sectorId % 120].create(Sector.SectorType.FILE);
        render(Sector.SectorState.CREATING);
        sectors[sectorId / 120][sectorId % 120].stay();
        render(Sector.SectorState.STAYING);
    }

    public void createFolder(Integer pointer) {
        int sectorId = pointer / sectorSize;
        sectors[sectorId / 120][sectorId % 120].create(Sector.SectorType.FOLDER);
        render(Sector.SectorState.CREATING);
        sectors[sectorId / 120][sectorId % 120].stay();
        render(Sector.SectorState.STAYING);
    }

    public void clear(Integer pointer) {
        int sectorId = pointer / sectorSize;
        sectors[sectorId / 120][sectorId % 120].remove();
        render(Sector.SectorState.REMOVING);
        sectors[sectorId / 120][sectorId % 120].stay();
        render(Sector.SectorState.STAYING);
    }

    public void move(Integer pointerFrom, Integer pointerTo) {
        int sectorFromId = pointerFrom / sectorSize;
        int sectorToId = pointerTo / sectorSize;

        sectors[sectorFromId / 120][sectorFromId % 120].move();
        sectors[sectorToId / 120][sectorToId % 120].create(sectors[sectorFromId / 120][sectorFromId % 120].getType());
        render(Sector.SectorState.MOVING);
        sectors[sectorToId / 120][sectorToId % 120].stay();
        sectors[sectorFromId / 120][sectorFromId % 120].remove();
        sectors[sectorFromId / 120][sectorFromId % 120].stay();
        render(Sector.SectorState.STAYING);
    }

    public void copy(Integer pointerFrom, Integer pointerTo) {
        int sectorFromId = pointerFrom / sectorSize;
        int sectorToId = pointerTo / sectorSize;

        sectors[sectorFromId / 120][sectorFromId % 120].copy();
        sectors[sectorToId / 120][sectorToId % 120].create(sectors[sectorFromId / 120][sectorFromId % 120].getType());
        render(Sector.SectorState.COPYING);
        sectors[sectorToId / 120][sectorToId % 120].stay();
        sectors[sectorFromId / 120][sectorFromId % 120].stay();
        render(Sector.SectorState.STAYING);
    }

    public void render(Sector.SectorState operation) {
        System.out.println(operation.toString() + ":");

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 120; j++) {
                switch (sectors[i][j].getState()) {
                    case CREATING:
                    case COPYING:
                    case MOVING:
                        System.out.print(Main.ANSI_BLUE_BACKGROUND + " " + Main.ANSI_RESET);
                        break;
                    case REMOVING:
                        System.out.print(Main.ANSI_RED_BACKGROUND + " " + Main.ANSI_RESET);
                        break;
                    case STAYING:
                        switch (sectors[i][j].getType()) {
                            case FILE:
                                System.out.print(Main.ANSI_CYAN_BACKGROUND + " " + Main.ANSI_RESET);
                                break;
                            case FOLDER:
                                System.out.print(Main.ANSI_YELLOW_BACKGROUND + " " + Main.ANSI_RESET);
                                break;
                            case EMPTY:
                                System.out.print(Main.ANSI_WHITE_BACKGROUND + " " + Main.ANSI_RESET);
                                break;
                        }
                        break;
                }
            }
            System.out.println();
        }
    }
}
