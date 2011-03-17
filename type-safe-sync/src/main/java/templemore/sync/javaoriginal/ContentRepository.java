package templemore.sync.javaoriginal;

/**
 * @author Chris Turner
 */
public class ContentRepository {

    public Story findStoryById(final String id) {
        return new Story(id);
    }

    public Image findImageById(final String id) {
        return new Image(id);
    }

    public BinaryObject findBinaryObjectById(final String id) {
        return new BinaryObject(id);
    }
}
