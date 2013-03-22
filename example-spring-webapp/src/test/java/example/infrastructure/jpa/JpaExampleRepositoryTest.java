package example.infrastructure.jpa;

import static org.fest.assertions.Assertions.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Iterables;

import example.domain.example.ExampleEntity;
import example.domain.example.ExampleRepository;
import example.domain.shared.ddd.EntityFunctions;

public class JpaExampleRepositoryTest extends AbstractJpaRepositoryTest {

	private static final String ANY_NAME = "any name";

	@Autowired
	private ExampleRepository exampleRepository;

	public void shouldSaveExample() {
		// given
		ExampleEntity exampleEntity = new ExampleEntity(ANY_NAME);

		// when
		ExampleEntity savedExampleEntity = exampleRepository.save(exampleEntity);

		// then
		assertThat(savedExampleEntity.isManaged()).isTrue();
	}

	public void shouldFindByName() {
		// given
		String name = "name";

		ExampleEntity exampleEntity = exampleRepository.save(new ExampleEntity(name));
		flushAndClear();

		// when
		List<ExampleEntity> results = exampleRepository.findByName(name);

		// then
		assertThat(results).isNotEmpty();
		assertThat(Iterables.transform(results, EntityFunctions.toEntityId())).contains(exampleEntity.getEntityId());
	}

}
