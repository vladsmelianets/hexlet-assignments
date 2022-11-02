package exercise;

// BEGIN
public final class App {

    public static synchronized void swapKeyValue(KeyValueStorage storage) {
        for (String key : storage.toMap().keySet()) {
            storage.set(storage.get(key, "default"), key);
            storage.unset(key);
        }
    }
}
// END
