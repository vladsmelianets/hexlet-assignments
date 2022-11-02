package exercise;

import java.util.Map;

// BEGIN
public final class SingleTag extends Tag {

    public SingleTag(String tagName, Map<String, String> tagAttributes) {
        super(tagName, tagAttributes);
    }

    @Override
    public String toString() {
        return stringifyAttributes();
    }
}

// END
