package example.infrastructure.jpa;

import static com.google.common.collect.Lists.*;
import static org.fest.assertions.Assertions.*;

import java.util.List;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.testng.annotations.Test;

import example.Assertions;

public class JpaTestRepositoryTest extends AbstractJpaRepositoryTest {

	private static final String ANY_NAME = "any name";

	private static final TestValueObject ANY_VALUE_OBJECT = new TestValueObject("any value", "any value");

	private static final DateTime ANY_DATE_TIME = new DateTime();

	private static final Money ANY_MONEY = Money.of(CurrencyUnit.EUR, 10);

	@Autowired
	private TestRepository testRepository;

	public void shouldSaveExample() {
		// given
		TestEntity example = new TestEntity(ANY_NAME);
		example.setTestValueObjectEmbedded(ANY_VALUE_OBJECT);
		example.setTestValueObjectJson(ANY_VALUE_OBJECT);
		example.setDateTime(ANY_DATE_TIME);
		example.setMoney(ANY_MONEY);

		// when
		TestEntity savedExample = testRepository.save(example);
		flushAndClear();

		// then
		assertThat(savedExample.isManaged()).isTrue();
		assertThat(savedExample.getAudit()).isEqualTo(EXPECTED_AUDIT);

		assertThat(savedExample.getName()).isEqualTo(ANY_NAME);
		assertThat(savedExample.getTestValueObjectEmbedded()).isEqualTo(ANY_VALUE_OBJECT);
		assertThat(savedExample.getTestValueObjectJson()).isEqualTo(ANY_VALUE_OBJECT);
		assertThat(savedExample.getDateTime()).isEqualTo(ANY_DATE_TIME);
		assertThat(savedExample.getMoney()).isEqualTo(ANY_MONEY);
	}

	@Test(expectedExceptions = DataIntegrityViolationException.class)
	public void shouldNotSaveExampleDuplicates() {
		// given
		String name = "duplicate";

		TestEntity exampleA = new TestEntity("duplicate");
		TestEntity exampleB = new TestEntity("duplicate");

		// when
		testRepository.save(newArrayList(exampleA, exampleB));
	}

	public void shouldFindByName() {
		// given
		String name = "name";
		TestEntity expectedExample = saveFlushAndClear(new TestEntity("name"));

		// when
		List<TestEntity> examples = testRepository.findByName(name);

		// then
		Assertions.assertThat(examples).containsWithSameIdentity(expectedExample);
	}

}
