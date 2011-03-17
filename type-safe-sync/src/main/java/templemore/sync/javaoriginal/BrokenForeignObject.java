package templemore.sync.javaoriginal;

/**
 * @author Chris Turner
 */
public class BrokenForeignObject implements ForeignObject {

    /**
     * Returns a class that is actually handled by a different foreign object.
     *
     * @return
     */
    @Override
    public Class<? extends Content> getLocalClass() {
        return Story.class;
    }
}
