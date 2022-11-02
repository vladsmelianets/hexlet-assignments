package exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// BEGIN
public final class App {

    public static boolean scrabble(String letters, String word) {
        List<String> lettersList = new ArrayList<>(Arrays.asList(letters.toLowerCase().split("")));
        for (String letter : word.toLowerCase().split("")) {
            if (!lettersList.remove(letter)) {
                return false;
            }
        }
        return true;
    }
}
//END
