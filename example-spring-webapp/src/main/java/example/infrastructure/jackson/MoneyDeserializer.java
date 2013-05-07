package example.infrastructure.jackson;

import java.io.IOException;

import org.joda.money.Money;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;

public class MoneyDeserializer extends StdScalarDeserializer<Money> {

	protected MoneyDeserializer() {
		super(Money.class);
	}

	private static final long serialVersionUID = 1L;

	@Override
	public Money deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		JsonToken t = jp.getCurrentToken();

		if (t == JsonToken.VALUE_STRING) {
			String str = jp.getText().trim();
			if (str.isEmpty()) {
				return null;
			} else {
				return Money.parse(str);
			}
		}
		throw ctxt.mappingException(getValueClass());
	}

}
