package exercise;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

// BEGIN
public final class Sorter {

    public static List<String> takeOldestMans(List<Map<String, String>> users) {
        return users.stream()
                .filter(user -> user.get("gender").equals("male"))
                .sorted(Comparator.comparing(user -> LocalDate.parse(user.get("birthday"))))
                .map(user -> user.get("name"))
                .toList();
    }
}
// END
