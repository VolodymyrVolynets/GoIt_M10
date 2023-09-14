package task3;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Key;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import shared.Utils;
import task2.User;

public class Task3 {
    public static void main(String[] args) {

        Map<String, Integer> wordCountMap = new HashMap<>();

        final String FILE_PATH = "src/main/java/task3/words.txt";
        try (FileReader reader = new FileReader(FILE_PATH)) {
            char[] buf = new char[256];
            int c;
            while ((c = reader.read(buf)) > 0) {
                if (c < 256) {
                    buf = Arrays.copyOf(buf, c);
                }
                String fileContent = Utils.charArrayToString(buf);
                wordCounter(fileContent, wordCountMap);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // Count and print words
        List<Map.Entry<String, Integer>> lst = mapToListSortedByValues(wordCountMap);
        for(Map.Entry<String, Integer> item: lst) {
            System.out.println(item.getKey() + " " + item.getValue());
        }
    }

    //Count how many times word occur in text
    private static void wordCounter(String str, Map<String, Integer> map) {
        String[] arr = str.trim().split(" |\n");

        for(String word: arr) {
            Integer counter = map.get(word.trim());
            map.put(word.trim(), counter != null ? counter + 1 : 1);
        }
    }

    // Convert map to list and sort by value
    private static List<Map.Entry<String, Integer>> mapToListSortedByValues(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> lst = new ArrayList<>(map.entrySet());
        lst.sort(((o1, o2) -> {
            return o2.getValue().compareTo(o1.getValue());
        }));
        return lst;
    }
}
