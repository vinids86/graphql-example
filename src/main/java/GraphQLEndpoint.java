import com.coxautodev.graphql.tools.SchemaParser;
import graphql.schema.GraphQLSchema;
import graphql.servlet.SimpleGraphQLServlet;
import org.jetbrains.annotations.NotNull;

import javax.servlet.annotation.WebServlet;

/**
 * Created by sara on set, 2018
 */

@WebServlet(urlPatterns = "/graphql")
public class GraphQLEndpoint extends SimpleGraphQLServlet {

    public GraphQLEndpoint() {
        super(buildSchema());
    }

    @NotNull
    private static GraphQLSchema buildSchema() {
        final LinkRepository linkRepository = new LinkRepository();
        return SchemaParser.newParser()
                .file("schema.graphql")
                .resolvers(new Query(linkRepository))
                .build()
                .makeExecutableSchema();
    }
}
