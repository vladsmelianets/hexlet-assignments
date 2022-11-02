package exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// BEGIN
public final class App {

    public static List<Map<String, String>> findWhere(List<Map<String, String>> books, Map<String, String> where) {
        List<Map<String, String>> foundBooks = new ArrayList<>();
        for (Map<String, String> book : books) {
            if (isMatch(book, where)) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    private static boolean isMatch(Map<String, String> book, Map<String, String> where) {
        for (String key : where.keySet()) {
            if (!book.get(key).equals(where.get(key))) {
                return false;
            }
        }
        return true;
    }
}

//END
