import java.util.ArrayList;
import java.util.List;

/**
 * Created by sara on set, 2018
 */
public class LinkRepository {

    private final List<Link> links;

    public LinkRepository() {
        this.links = new ArrayList<>();
        links.add(new Link("http://howtographql.com", "Your favorite GraphQL page"));
        links.add(new Link("http://graphql.org/learn/", "The official docks"));
    }

    public List<Link> getAllLinks() {
        return links;
    }

    public void saveLink(Link link) {
        links.add(link);
    }
}
