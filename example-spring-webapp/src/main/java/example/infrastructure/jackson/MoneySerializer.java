package example.infrastructure.jackson;

import java.io.IOException;

import org.joda.money.Money;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class MoneySerializer extends StdSerializer<Money> {

	protected MoneySerializer() {
		super(Money.class);
	}

	@Override
	public void serialize(Money value, JsonGenerator jgen, SerializerProvider provider) throws IOException,
			JsonGenerationException {
		jgen.writeString(value.toString());
	}

}
