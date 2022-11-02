package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public final class App {

    public static Map<String, Integer> getWordCount(String sentence) {
        Map<String, Integer> wordCount = new HashMap<>();
        if (sentence.isEmpty()) {
            return wordCount;
        }
        for (String word : sentence.split("\\s")) {
            int count = wordCount.getOrDefault(word, 0);
            wordCount.put(word, count + 1);
        }
        return wordCount;
    }

    public static String toString(Map<String, Integer> wordCount) {
        if (wordCount.isEmpty()) {
            return "{}";
        }
        StringBuilder result = new StringBuilder();
        result.append("{").append(System.lineSeparator());
        for (String word : wordCount.keySet()) {
            result.append("  ").append(word).append(": ").append(wordCount.get(word)).append(System.lineSeparator());
        }
        result.append("}");
        return result.toString();
    }
}
//END
