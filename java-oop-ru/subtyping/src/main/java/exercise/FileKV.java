package exercise;

import java.util.Map;

// BEGIN
public final class FileKV implements KeyValueStorage {

    private final String filePath;

    public FileKV(String path, Map<String, String> initialDb) {
        this.filePath = path;
        Utils.writeFile(filePath, Utils.serialize(initialDb));
        // optional: could use already defined this.set()
        // initialDb.entrySet().stream().forEach(entry -> set(entry.getKey(), entry.getValue()));
    }

    @Override
    public void set(String key, String value) {
        Map<String, String> map = Utils.unserialize(Utils.readFile(filePath));
        map.put(key, value);
        Utils.writeFile(filePath, Utils.serialize(map));
    }

    @Override
    public void unset(String key) {
        Map<String, String> map = Utils.unserialize(Utils.readFile(filePath));
        map.remove(key);
        Utils.writeFile(filePath, Utils.serialize(map));
    }

    @Override
    public String get(String key, String defaultValue) {
        return Utils.unserialize(Utils.readFile(filePath)).getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return Utils.unserialize(Utils.readFile(filePath));
    }
}
// END
