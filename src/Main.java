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
        Drive drive = new Drive(100000);
        drive.createFile(1);
        drive.createFolder(1000);
        drive.createFolder(2000);
        drive.move(1000, 3000);
        drive.clear(1);
        drive.copy(3000, 1);
    }
}
