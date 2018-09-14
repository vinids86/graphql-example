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

	public List<Link> allLinks(LinkFilter filter, Number skip, Number first) {
		return linkRepository.getAllLinks(filter, skip.intValue(), first.intValue());
	}
}
