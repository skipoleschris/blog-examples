package templemore.sync.javaoriginal;

/**
 * @author Chris Turner
 */
public class Image implements Content {

    private final String id;

    public Image(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
