import com.coxautodev.graphql.tools.GraphQLRootResolver;

/**
 * Created by sara on set, 2018
 */
public class Mutation implements GraphQLRootResolver {

    private final LinkRepository linkRepository;

    public Mutation(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public Link createLink(String url, String description) {
        final Link newLink = new Link(url, description);
        linkRepository.saveLink(newLink);
        return newLink;
    }
}
