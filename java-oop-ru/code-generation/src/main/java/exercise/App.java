package exercise;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

// BEGIN
public class App {

    public static void save(Path tempPath, Car car) throws IOException {
        Files.writeString(tempPath, car.serialize(), StandardOpenOption.WRITE);
    }

    public static Car extract(Path path) throws IOException {
        return Car.unserialize(Files.readString(path));
    }
}
// END
