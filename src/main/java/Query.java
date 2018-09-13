import java.util.List;

import com.coxautodev.graphql.tools.GraphQLRootResolver;

/**
 * Created by sara on set, 2018
 */
public class Query implements GraphQLRootResolver {

	private final LinkRepository linkRepository;

	public Query(LinkRepository linkRepository) {
		this.linkRepository = linkRepository;
	}

	public List<Link> allLinks(LinkFilter filter) {
		return linkRepository.getAllLinks(filter);
	}
}
