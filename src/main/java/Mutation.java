import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import com.coxautodev.graphql.tools.GraphQLRootResolver;

import graphql.GraphQLException;
import graphql.schema.DataFetchingEnvironment;

/**
 * Created by sara on set, 2018
 */
public class Mutation implements GraphQLRootResolver {

	private final LinkRepository linkRepository;
	private final UserRepository userRepository;
	private final VoteRepository voteRepository;

	public Mutation(LinkRepository linkRepository, UserRepository userRepository,
			VoteRepository voteRepository) {
		this.linkRepository = linkRepository;
		this.userRepository = userRepository;
		this.voteRepository = voteRepository;
	}

	public Link createLink(String url, String description, DataFetchingEnvironment env) {
		final AuthContext context = env.getContext();
		final Link newLink = new Link(url, description, context.getUser().getId());
		linkRepository.saveLink(newLink);
		return newLink;
	}

	public User createUser(String name, AuthData auth) {
		final User newUser = new User(name, auth.getEmail(), auth.getPassword());
		return userRepository.saveUser(newUser);
	}

	public SigninPayload signinUser(AuthData auth) throws IllegalAccessException {
		final User user = userRepository.findByEmail(auth.getEmail());
		if (user.getPassword().equals(auth.getPassword())) {
			return new SigninPayload(user.getId(), user);
		}
		throw new GraphQLException("Invalid credentials");
	}

	public Vote createVote(String linkId, String userId) {
		final ZonedDateTime now = Instant.now().atZone(ZoneOffset.UTC);
		return voteRepository.saveVote(new Vote(now, userId, linkId));
	}
}
