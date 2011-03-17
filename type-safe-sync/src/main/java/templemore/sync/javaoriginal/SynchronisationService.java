package templemore.sync.javaoriginal;

/**
 * @author Chris Turner
 */
public class SynchronisationService {

    private final ContentRepository repository;

    public SynchronisationService(final ContentRepository repository) {
        this.repository = repository;
    }

    @SuppressWarnings( { "unchecked" })
    public Content sync(final ForeignObject foreignObject) {
        return findById(foreignObject.getLocalClass(), foreignObject);
    }

    @SuppressWarnings("unchecked")
    private <T extends Content> T findById(final Class<T> clazz, final ForeignObject foreignObject) {
        if(Story.class.equals(clazz)) {
            CMSForeignObject cmsForeignObject = (CMSForeignObject)foreignObject;
            return (T)repository.findStoryById(cmsForeignObject.getId());
        }
        else if(Image.class.equals(clazz)) {
            CMSForeignObject cmsForeignObject = (CMSForeignObject)foreignObject;
            return (T)repository.findImageById(cmsForeignObject.getId());
        }
        else if(BinaryObject.class.equals(clazz)) {
            CMSForeignObject cmsForeignObject = (CMSForeignObject)foreignObject;
            return (T)repository.findBinaryObjectById(cmsForeignObject.getId());
        }
        else {
            throw new IllegalStateException(
                "We have managed to try and synchronise an unsupported class. This is a bug.");
        }
    }
}
