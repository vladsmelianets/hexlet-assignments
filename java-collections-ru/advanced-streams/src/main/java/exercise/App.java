package exercise;

import java.util.stream.Collectors;
import java.util.stream.Stream;

// BEGIN
public final class App {

    public static String getForwardedVariables(String data) {
        return data.lines()
                .filter(line -> line.startsWith("environment="))
                .map(line -> line.replace("environment=", ""))
                .map(line -> line.replace("\"", ""))
                .flatMap(line -> Stream.of(line.split(",")))
                .filter(prop -> prop.startsWith("X_FORWARDED_"))
                .map(prop -> prop.replace("X_FORWARDED_", ""))
                .collect(Collectors.joining(","));
    }
}
//END
