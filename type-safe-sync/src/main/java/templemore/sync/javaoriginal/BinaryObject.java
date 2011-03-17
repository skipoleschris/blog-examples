package templemore.sync.javaoriginal;

/**
 * @author Chris Turner
 */
public class BinaryObject implements Content {

    private final String id;

    public BinaryObject(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
