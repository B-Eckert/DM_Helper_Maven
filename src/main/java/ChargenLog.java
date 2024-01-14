import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/*
 * Brant Eckert February 2020
 * Outputs everything into a file called the ChargenLog; in a consistent place on my computer, and I believe it creates
 * a new directory if one doesn't previously exist
 */

public class ChargenLog {
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private static LocalDateTime now = LocalDateTime.now();

    /**
     * Ensures that the file exists
     * @throws IOException Exception thrown if the file does not exist or cannot be accessed
     */
    public static void ensureFile() throws IOException {
        Path p = Paths.get(FILE_NAME);
        File dir = new File(DIR_NAME);
        if(!dir.exists()){
            dir.mkdir();
        }
        try{
            Files.readAllLines(p);
        } catch (NoSuchFileException e) {
            File n = new File(FILE_NAME);
            n.createNewFile();
            now = LocalDateTime.now();
            log("File created at " + dtf.format(now));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tries to read a relatively "Short" file
     * @return The contents of the file, line-by-line, presented in a list
     * @throws IOException Exception thrown if file is inaccessible.
     */
    public static List<String> tryReadingShort() throws IOException {
        ensureFile();
        Path p = Paths.get(FILE_NAME);
        return Files.readAllLines(p);
    }

    /**
     * Tries to write to a relatively 'short' file.
     * @param lines The lines to write into the file.
     * @throws IOException Exception thrown when writing cannot occur.
     */
    public static void tryWritingShort(List<String> lines) throws IOException {
        ensureFile();
        Path p = Paths.get(FILE_NAME);
        List<String> n = tryReadingShort();
        List<String> artifact = new ArrayList<>();
        while(!n.isEmpty()){
            artifact.add(n.remove(0));
        }
        while(!lines.isEmpty()) {
            artifact.add(lines.remove(0));
        }
        Files.write(p, artifact);
    }

    /**
     * Adds a log to the chargenlog without a time. This log is usually a character or randomly generated loot.
     * @param log The string to log.
     * @throws IOException Exception thrown when a file IO error occurs
     */
    public static void log(String log) throws IOException {
        List<String> n = new ArrayList<>();
        n.add(log);
        n.add("-------------------------------------------");
        tryWritingShort(n);
    }

    /**
     * Adds a log to chargenlog with a timestamp.
     * @param inLog The log to add
     * @throws IOException Exception thrown when file IO error occurs.
     */
    public static void logWithTime(String inLog) throws IOException{
        now = LocalDateTime.now();
        log("Entry made at " + dtf.format(now));
        log(inLog);
    }

    /**
     * Unsure why you'd want to do this but prints out the entire file to the console.
     * @throws IOException Exception thrown when file IO error occurs.
     */
    public static void print() throws IOException{
        List<String> n = tryReadingShort();
        while(!n.isEmpty()){
            System.out.println(n.remove(0));
        }
    }

    final static Charset ENCODING = StandardCharsets.UTF_8; // defunct
    final static String DIR_NAME = System.getProperty("user.dir") + "\\storage\\";
    final static String FILE_NAME = DIR_NAME + "chargenlog.txt";
}
