package example.infrastructure.jackson;

import static org.fest.assertions.Assertions.*;

import java.math.BigDecimal;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import example.TestGroups;

@ContextConfiguration(locations = "classpath:/META-INF/spring/testContext-jackson.xml")
@Test(groups = TestGroups.INTEGRATION, singleThreaded = true)
@ActiveProfiles("test")
public class Jackson2JsonSerializationServiceTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private Jackson2JsonSerializationService service;

	@DataProvider
	static final Object[][] objects() {
		return new Object[][] { { Money.of(CurrencyUnit.EUR, BigDecimal.ZERO) }, { new DateTime(DateTimeZone.UTC) },
				{ new LocalDate() } };
	}

	@Test(dataProvider = "objects")
	public void shouldSerializeAndDeserialize(Object object) {
		assertThat(service.fromJson(service.toJson(object), object.getClass().getName())).isEqualTo(object);
	}
}
