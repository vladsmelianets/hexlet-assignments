package exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

// BEGIN
public final class App {

    public static Map<String, String> genDiff(Map<String, Object> firstDataMap, Map<String, Object> secondDataMap) {
        Set<String> keys = new TreeSet<>(firstDataMap.keySet());
        keys.addAll(secondDataMap.keySet());

        Map<String, String> difference = new LinkedHashMap<>();
        for (String key : keys) {
            if (!secondDataMap.containsKey(key)) {
                difference.put(key, "deleted");
            } else if (!firstDataMap.containsKey(key)) {
                difference.put(key, "added");
            } else if (firstDataMap.get(key).equals(secondDataMap.get(key))) {
                difference.put(key, "unchanged");
            } else {
                difference.put(key, "changed");
            }
        }
        return difference;
    }
}
//END
