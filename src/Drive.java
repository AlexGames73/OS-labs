import java.util.ArrayList;
import java.util.List;

public class Drive {
    private Sector[] sectors;
    private List<File> files;
    private List<Directory> directories;
    private int driveSize;

    public Drive(int driveSize) {
        this.driveSize = driveSize;
        sectors = new Sector[driveSize];

        for (int i = 0; i < driveSize; i++) {
            sectors[i] = new Sector("", Sector.SectorType.EMPTY, null);
        }
        files = new ArrayList<>();
        directories = new ArrayList<>();
    }

    public File createFile(String name, Directory directory, String content) throws InterruptedException {
        List<Sector> empty = getEmptySectors();
        int countSectors = content.length() / Sector.SECTOR_SIZE + (content.length() % Sector.SECTOR_SIZE > 0 ? 1 : 0);
        if (empty.size() < countSectors) {
            throw new StackOverflowError();
        }

        File file = new File(name, directory);
        file.setHead(empty.get(0));

        for (int i = 0; i < countSectors; i++) {
            Sector current = empty.get(i);
            if (i != 0)
                empty.get(i - 1).setNextSector(current);
            current.setContent(content.substring(0, Math.min(content.length(), 10)));
            content = content.substring(Math.min(content.length(), 10));
        }

        files.add(file);
        file.getHead().create(Sector.SectorType.FILE);
        render(Sector.SectorState.CREATING);
        file.getHead().stay();
        return file;
    }

    public Directory createDirectory(String name, Directory directory) {
        Directory newDirectory = new Directory(name);
        if (directory != null) {
            newDirectory.setParent(directory);
            directory.pushChild(newDirectory);
        }

        directories.add(newDirectory);
        return newDirectory;
    }

    public Directory copyDirectory(Directory current, Directory to) throws InterruptedException {
        Directory directory = createDirectory(current.getName(), to);
        List<File> cloneFiles = files.subList(0, files.size() - 1);
        for (File file : cloneFiles) {
            if (file.getDirectory() == current) {
                copyFile(file, directory);
            }
        }

        for (Directory folder : current.getChilds()) {
            copyDirectory(folder, directory);
        }
        return directory;
    }


    public File copyFile(File file, Directory directory) throws InterruptedException {
        File newFile = createFile(file.getName(), directory, file.getContent());
        newFile.getHead().copy();
        file.getHead().copy();
        render(Sector.SectorState.COPYING);
        newFile.getHead().stay();
        file.getHead().stay();
        return newFile;
    }

    public File moveFile(File file, Directory directory) throws InterruptedException {
        File newFile = copyFile(file, directory);
        removeFile(file);
        return newFile;
    }

    public Directory moveDirectory(Directory current, Directory to) throws InterruptedException {
        Directory newDirectory = copyDirectory(current, to);
        removeDirectory(current);
        return newDirectory;
    }

    public void removeFile(File file) {
        file.getHead().remove();
        render(Sector.SectorState.REMOVING);
        files.remove(file);
    }

    public void removeDirectory(Directory directory) {
        List<File> cloneFiles = files.subList(0, files.size() - 1);
        for (File file : cloneFiles) {
            if (file.getDirectory() == directory) {
                removeFile(file);
            }
        }

        for (Directory current : directory.getChilds()) {
            removeDirectory(current);
        }

        directories.remove(directory);
    }

    public void selectFile(File file) {
        file.getHead().select();
        render(Sector.SectorState.SELECTING);
        file.getHead().stay();
    }

    public void render(Sector.SectorState state) {
        System.out.println(state.toString() + ":");
        long countRows = Math.round(Math.ceil(driveSize / 100.));

        for (int i = 0; i < countRows; i++) {
            for (int j = 0; j < 100; j++) {
                switch (sectors[i * 100 + j].getState()) {
                    case CREATING, COPYING, MOVING -> System.out.print(Main.ANSI_BLUE_BACKGROUND + " " + Main.ANSI_RESET);
                    case SELECTING -> System.out.print(Main.ANSI_RED_BACKGROUND + " " + Main.ANSI_RESET);
                    case REMOVING, STAYING -> System.out.print(Main.ANSI_WHITE_BACKGROUND + " " + Main.ANSI_RESET);
                }
            }
            System.out.println();
        }
    }

    private List<Sector> getEmptySectors() {
        List<Sector> res = new ArrayList<>();
        for (int i = 0; i < driveSize; i++) {
            if (sectors[i].getType() == Sector.SectorType.EMPTY) {
                res.add(sectors[i]);
            }
        }
        return res;
    }
}

