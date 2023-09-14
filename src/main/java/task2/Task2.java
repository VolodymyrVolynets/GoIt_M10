package task2;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import shared.Utils;

public class Task2 {
    public static void main(String[] args) {
        final String FILE_PATH = "src/main/java/task2/file.txt";
        final String OUTPUT_FILE_PATH = "src/main/java/task2/users.json";
        try (FileReader reader = new FileReader(FILE_PATH)) {
            char[] buf = new char[256];
            int c;
            while ((c = reader.read(buf)) > 0) {
                if (c < 256) {
                    buf = Arrays.copyOf(buf, c);
                }
                String fileContent = Utils.charArrayToString(buf);

                // index 0 = age name; index 1 = actualName actualName, etc...
                String[] arr = fileContent.trim().split("\\n");

                if (arr.length == 1) return;
                List<User> userList = new ArrayList<>();

                for(int i = 1; i < arr.length; i++) {
                    String[] userDetails = arr[i].trim().split(" ");
                    try {
                        userList.add(new User(userDetails[0], Integer.parseInt(userDetails[1])));
                    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                        System.out.println(e);
                    }
                }

                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String json = gson.toJson(userList);

                writeFile(OUTPUT_FILE_PATH, json);

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void writeFile(String filePath, String str) {
        File file = new File(filePath);
        //try-with-resources
        try (FileWriter writer = new FileWriter(file))
        {
            writer.write(str);
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
