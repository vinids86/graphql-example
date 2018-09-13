import com.fasterxml.jackson.annotation.JsonIgnore;

import graphql.ExceptionWhileDataFetching;

/**
 * Created by sara on set, 2018
 */
public class SanitizedError extends ExceptionWhileDataFetching {

	public SanitizedError(ExceptionWhileDataFetching inner) {
		super(inner.getException());
	}

	@Override
	@JsonIgnore
	public Throwable getException() {
		return super.getException();
	}
}
