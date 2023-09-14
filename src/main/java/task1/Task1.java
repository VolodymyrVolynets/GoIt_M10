package task1;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import shared.Utils;

public class Task1 {
    public static void main(String[] args) {
        final String FILE_PATH = "src/main/java/task1/file.txt";
        try (FileReader reader = new FileReader(FILE_PATH)) {
            char[] buf = new char[256];
            int c;
            while ((c = reader.read(buf)) > 0) {
                if (c < 256) {
                    buf = Arrays.copyOf(buf, c);
                }
                String fileContent = Utils.charArrayToString(buf);
                printPhoneNumbers(fileContent);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printPhoneNumbers(String s) {
        final String PATTERN = "\\(\\d{3}\\) \\d{3}-\\d{4}|\\d{3}-\\d{3}-\\d{4}";
        Pattern p = Pattern.compile(PATTERN);
        Matcher m = p.matcher(s);
        while (m.find()) {
            System.out.println(m.group());
        }
    }
}
