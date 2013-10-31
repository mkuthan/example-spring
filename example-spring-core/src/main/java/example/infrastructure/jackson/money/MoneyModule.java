package example.infrastructure.jackson.money;

import org.joda.money.Money;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class MoneyModule extends SimpleModule {

	private static final long serialVersionUID = 1L;

	public MoneyModule() {
		super(Version.unknownVersion());

		addDeserializer(Money.class, new MoneyDeserializer());
		addSerializer(Money.class, new MoneySerializer());
	}

}
