package templemore.sync.javaoriginal;

import java.util.UUID;

/**
 * @author Chris Turner
 */
public class CMSForeignObject implements ForeignObject {

    private final String type;
    private final String id;

    public CMSForeignObject(final String type) {
        this.type = type;
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    @Override
    public Class<? extends Content> getLocalClass() {
        if ("Story".equals(type)) return Story.class;
        else if ("Image".equals(type)) return Image.class;
        else if ("BinaryObject".equals(type)) return BinaryObject.class;
        else if ("RelatedLinks".equals(type)) return RelatedLinks.class;
        else throw new IllegalStateException("Unknown type: " + type);
    }
}
