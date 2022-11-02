package exercise;

import java.util.List;
import java.util.Map;

// BEGIN
public final class PairedTag extends Tag {

    private final String body;
    private final List<Tag> children;

    public PairedTag(String tagName, Map<String, String> tagAttributes, String tagBody, List<Tag> tagChildren) {
        super(tagName, tagAttributes);
        this.body = tagBody;
        this.children = tagChildren;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(stringifyAttributes());
        sb.append(body);
        children.forEach(child -> sb.append(child.toString()));
        sb.append("</")
                .append(name)
                .append(">");
        return sb.toString();
    }
}
// END
