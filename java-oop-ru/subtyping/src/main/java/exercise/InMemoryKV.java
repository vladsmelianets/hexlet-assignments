package exercise;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// BEGIN
public final class InMemoryKV implements KeyValueStorage {

    private Map<String, String> map;

    public InMemoryKV(Map<String, String> initialDb) {
        this.map = new HashMap<>(Collections.unmodifiableMap(initialDb));
    }

    @Override
    public void set(String key, String value) {
        map.put(key, value);
    }

    @Override
    public void unset(String key) {
        map.remove(key);
    }

    @Override
    public String get(String key, String defaultValue) {
        return map.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(Collections.unmodifiableMap(map));
    }
}
// END
