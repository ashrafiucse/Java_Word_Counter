import java.io.*;
import java.util.*;

public class WordCounter {
    public static void main(String[] args) {
        // Check if file path is provided
        if (args.length != 1) {
            System.out.println("Usage: java WordCounter <file-path>");
            return;
        }

        String filePath = args[0];
        HashMap<String, Integer> wordCountMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Convert the line to lowercase and replace all non-word characters with space
                String[] words = line.toLowerCase().replaceAll("[^a-zA-Z\\s]", "").split("\\s+");
                for (String word : words) {
                    if (!word.isEmpty()) {
                        wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Create a list from the elements of the map
        List<Map.Entry<String, Integer>> list = new ArrayList<>(wordCountMap.entrySet());

        // Sort the list by the key (word)
        list.sort(Map.Entry.comparingByKey());

        // Print the sorted words along with their occurrences
        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
