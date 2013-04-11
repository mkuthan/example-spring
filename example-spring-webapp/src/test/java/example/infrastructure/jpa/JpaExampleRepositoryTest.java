package example.infrastructure.jpa;

import static com.google.common.collect.Lists.newArrayList;
import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.fest.assertions.Assertions.assertThat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import example.assertions.Assertions;
import example.domain.example.ExampleEntity;
import example.domain.example.ExampleRepository;
import example.domain.example.ExampleValueObject;

public class JpaExampleRepositoryTest extends AbstractJpaRepositoryTest {

	private static final String ANY_NAME = "any name";

	@Autowired
	private ExampleRepository exampleRepository;

	@DataProvider
	public Object[][] valueObjects() {
		return new Object[][] { { new ExampleValueObject("fieldA", "fieldB") },
				{ new ExampleValueObject("fieldA", "fieldB", "fieldC") } };
	}

	public void shouldSaveExample() {
		// given
		ExampleEntity example = new ExampleEntity(ANY_NAME);

		// when
		ExampleEntity savedExample = exampleRepository.save(example);
		flushAndClear();

		// then
		assertThat(savedExample.isManaged()).isTrue();
		assertThat(savedExample.getName()).isEqualTo(ANY_NAME);
	}

	@Test
	public void shouldNotSaveExampleDuplicates() {
		// given
		ExampleEntity exampleA = new ExampleEntity(ANY_NAME);
		ExampleEntity exampleB = new ExampleEntity(ANY_NAME);

		// when
		catchException(exampleRepository).save(newArrayList(exampleA, exampleB));

		// then
		assertThat(caughtException()).isInstanceOf(DataIntegrityViolationException.class);
	}

	public void shouldFindByName() {
		// given
		String name = "name";

		ExampleEntity expectedExample = exampleRepository.save(new ExampleEntity(name));
		flushAndClear();

		// when
		List<ExampleEntity> examples = exampleRepository.findByName(name);

		// then
		Assertions.assertThat(examples).containsWithSameIdentity(expectedExample);
	}

	@Test(dataProvider = "valueObjects")
	public void shouldFindByValueObject(ExampleValueObject exampleValueObject) {
		// given
		ExampleEntity example = new ExampleEntity(ANY_NAME);
		example.setValue(exampleValueObject);

		ExampleEntity expectedExample = saveFlushAndClear(example);
		flushAndClear();

		// when
		List<ExampleEntity> examples = exampleRepository.findByValue(exampleValueObject);

		// then
		Assertions.assertThat(examples).containsWithSameIdentity(expectedExample);
	}

}
