import com.coxautodev.graphql.tools.GraphQLResolver;

/**
 * Created by sara on set, 2018
 */
public class SigninResolver implements GraphQLResolver<SigninPayload> {

	public User user(SigninPayload payload) {
		return payload.getUser();
	}
}
