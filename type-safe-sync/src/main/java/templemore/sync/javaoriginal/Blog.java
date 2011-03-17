package templemore.sync.javaoriginal;

/**
 * @author Chris Turner
 */
public class Blog implements Content {

    private final String title;
    private final String author;

    public Blog(final String title, final String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}
