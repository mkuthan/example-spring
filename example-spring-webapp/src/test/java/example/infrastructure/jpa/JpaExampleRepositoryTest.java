package example.infrastructure.jpa;

import static com.google.common.collect.Lists.newArrayList;
import static org.fest.assertions.Assertions.assertThat;

import java.util.List;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import example.assertions.Assertions;
import example.domain.example.ExampleEntity;
import example.domain.example.ExampleEntity.Builder;
import example.domain.example.ExampleRepository;
import example.domain.example.ExampleValueObject;

public class JpaExampleRepositoryTest extends AbstractJpaRepositoryTest {

	private static final String ANY_NAME = "any name";

	private static final ExampleValueObject ANY_VALUE_OBJECT = new ExampleValueObject.Builder().withFieldA("FieldA")
			.withFieldB("FieldB").withFieldC("FieldC").build();

	private static final DateTime ANY_DATE_TIME = new DateTime();

	private static final Money ANY_MONEY = Money.of(CurrencyUnit.EUR, 10);

	@Autowired
	private ExampleRepository exampleRepository;

	@DataProvider
	public Object[][] valueObjects() {
		ExampleValueObject.Builder exampleValueObjectBuilder = new ExampleValueObject.Builder().withFieldA("FieldA")
				.withFieldB("FieldB").withFieldC("FieldC");

		return new Object[][] { { exampleValueObjectBuilder.build() }
		// https://hibernate.atlassian.net/browse/HHH-8172
		// { exampleValueObjectBuilder.withFieldC(null).build() }
		};
	}

	public void shouldSaveExample() {
		// given
		ExampleEntity example = createBuilder().build();

		// when
		ExampleEntity savedExample = exampleRepository.save(example);
		flushAndClear();

		// then
		assertThat(savedExample.isManaged()).isTrue();
		assertThat(savedExample.getAudit()).isEqualTo(EXPECTED_AUDIT);

		assertThat(savedExample.getName()).isEqualTo(ANY_NAME);
		assertThat(savedExample.getValue()).isEqualTo(ANY_VALUE_OBJECT);
		assertThat(savedExample.getJson()).isEqualTo(ANY_VALUE_OBJECT);
		assertThat(savedExample.getDateTime()).isEqualTo(ANY_DATE_TIME);
		assertThat(savedExample.getMoney()).isEqualTo(ANY_MONEY);
	}

	@Test(expectedExceptions = DataIntegrityViolationException.class)
	public void shouldNotSaveExampleDuplicates() {
		// given
		String name = "duplicate";

		ExampleEntity exampleA = createBuilder().withName(name).build();
		ExampleEntity exampleB = createBuilder().withName(name).build();

		// when
		exampleRepository.save(newArrayList(exampleA, exampleB));
	}

	public void shouldFindByName() {
		// given
		String name = "name";
		ExampleEntity expectedExample = saveFlushAndClear(createBuilder().withName(name).build());

		// when
		List<ExampleEntity> examples = exampleRepository.findByName(name);

		// then
		Assertions.assertThat(examples).containsWithSameIdentity(expectedExample);
	}

	@Test(dataProvider = "valueObjects")
	public void shouldFindByValueObject(ExampleValueObject exampleValueObject) {
		// given
		ExampleEntity expectedExample = saveFlushAndClear(createBuilder().withEmbeddedValueObject(exampleValueObject)
				.build());

		// when
		List<ExampleEntity> examples = exampleRepository.findByEmbeddedValueObject(exampleValueObject);

		// then
		Assertions.assertThat(examples).containsWithSameIdentity(expectedExample);
	}

	private Builder createBuilder() {
		return new Builder().withName(ANY_NAME).withEmbeddedValueObject(ANY_VALUE_OBJECT)
				.withJsonValueObject(ANY_VALUE_OBJECT).withDateTime(ANY_DATE_TIME).withMoney(ANY_MONEY);
	}

}
