package templemore.sync.javaoriginal;

/**
 * @author Chris Turner
 */
public class Story implements Content {

    private final String id;

    public Story(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
