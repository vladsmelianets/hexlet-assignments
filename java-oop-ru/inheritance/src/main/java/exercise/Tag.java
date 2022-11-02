package exercise;

import java.util.Map;

// BEGIN
public abstract class Tag {

    protected final String name;
    protected final Map<String, String> attributes;

    protected Tag(String tagName, Map<String, String> tagAttributes) {
        this.name = tagName;
        this.attributes = tagAttributes;
    }

    public String stringifyAttributes() {
        StringBuilder sb = new StringBuilder("<").append(name);
        attributes.forEach((k, v) -> sb
                .append(" ")
                .append(k)
                .append("=\"")
                .append(v)
                .append("\""));
        return sb.append(">").toString();
    }
}
// END
