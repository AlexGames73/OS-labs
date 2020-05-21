/**
 * Цвет White обозначает пустой сектор
 * Цвет Cyan обозначает сектор с файлом
 * Цвет Yellow обозначает сектор с директорией
 * Цвет Blue обозначает сектор над которым производится действие Create/Copy/Move
 * Цвет Red обозначает сектор над которым производится действие Remove
 */
public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void main(String[] args) throws Exception {
        Drive drive = new Drive(1000);
        Directory main = drive.createDirectory("dirMain", null);
        File file1 = drive.createFile("file1", main, "hello world!!!");
        Directory src = drive.createDirectory("src", main);
        drive.selectFile(file1);
        file1 = drive.moveFile(file1, src);
        drive.selectFile(file1);
        File file1_2 = drive.copyFile(file1, main);
        drive.selectFile(file1);
        drive.removeFile(file1);
        drive.selectFile(file1_2);
        Directory kek = drive.createDirectory("kek", main);
        file1_2 = drive.moveFile(file1_2, src);
        drive.moveDirectory(src, kek);
        drive.removeDirectory(src);
    }
}
