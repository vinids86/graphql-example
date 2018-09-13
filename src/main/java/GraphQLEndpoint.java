import java.util.Optional;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jetbrains.annotations.NotNull;

import com.coxautodev.graphql.tools.SchemaParser;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import graphql.schema.GraphQLSchema;
import graphql.servlet.GraphQLContext;
import graphql.servlet.SimpleGraphQLServlet;

/**
 * Created by sara on set, 2018
 */

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {

	private static final LinkRepository linkRepository;
	private static final UserRepository userRepository;
	private static final VoteRepository voteRepository;

	static {
		final MongoDatabase mongo = new MongoClient().getDatabase("graphql-example");
		linkRepository = new LinkRepository(mongo.getCollection("links"));
		userRepository = new UserRepository(mongo.getCollection("users"));
		voteRepository = new VoteRepository(mongo.getCollection("votes"));
	}

	public GraphQLEndpoint() {
		super(buildSchema());
	}

	@NotNull
	private static GraphQLSchema buildSchema() {
		return SchemaParser.newParser().file("schema.graphqls")
				.resolvers(new Query(linkRepository),
						new Mutation(linkRepository, userRepository, voteRepository),
						new SigninResolver(), new LinkResolver(userRepository),
						new VoteResolver(linkRepository, userRepository))
				.scalars(Scalars.dateTime).build().makeExecutableSchema();
	}

	@Override
	protected GraphQLContext createContext(Optional<HttpServletRequest> request,
			Optional<HttpServletResponse> response) {

		User user = request.map(req -> req.getHeader("Authorization"))
				.filter(id -> !id.isEmpty()).map(id -> id.replace("Bearer ", ""))
				.map(userRepository::findById).orElse(null);

		return new AuthContext(user, request, response);
	}
}
