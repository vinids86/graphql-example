import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;

/**
 * Created by sara on set, 2018
 */
public class Scalars {
	public static GraphQLScalarType dateTime = new GraphQLScalarType("DateTime",
			"DataTime scalar", new Coercing() {
				@Override
				public Object serialize(Object input) {
					return ((ZonedDateTime) input)
							.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
				}

				@Override
				public Object parseValue(Object input) {
					return serialize(input);
				}

				@Override
				public Object parseLiteral(Object input) {
					if (input instanceof StringValue) {
						return ZonedDateTime.parse(((StringValue) input).getValue());
					}
					return null;
				}
			});
}
