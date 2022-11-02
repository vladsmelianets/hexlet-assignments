package exercise;

import java.util.Arrays;
import java.util.stream.Stream;

// BEGIN
public final class App {

    public static String[][] enlargeArrayImage(String[][] image) {
        return Arrays.stream(image)
                .flatMap(line -> Stream.of(repeatElement(line), repeatElement(line)))
                .toArray(String[][]::new);
    }

    private static String[] repeatElement(String[] line) {
        return Arrays.stream(line)
                .flatMap(string -> Stream.of(string, string))
                .toArray(String[]::new);
    }
}

// END
