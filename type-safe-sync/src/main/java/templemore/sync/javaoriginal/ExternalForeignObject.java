package templemore.sync.javaoriginal;

import java.net.URL;

/**
 * @author Chris Turner
 */
public class ExternalForeignObject implements ForeignObject {

    private final String type;
    private final URL url;

    public ExternalForeignObject(final String type, final URL url) {
        this.type = type;
        this.url = url;
    }

    public URL getUrl() {
        return url;
    }

    @Override
    public Class<? extends Content> getLocalClass() {
        if ("Blog".equals(type)) return Blog.class;
        else throw new IllegalStateException("Unknown type: " + type);
    }
}
