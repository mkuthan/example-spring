package example.infrastructure.jpa.iam;

import static com.google.common.collect.Lists.newArrayList;
import static org.fest.assertions.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.testng.annotations.Test;

import example.EntitiesAssert;
import example.EntityAssert;
import example.domain.iam.model.Group;
import example.domain.iam.model.User;
import example.domain.iam.model.User.Builder;
import example.domain.iam.model.UserIdentifier;
import example.domain.iam.model.UserRepository;
import example.infrastructure.jpa.AbstractJpaRepositoryTest;

public class JpaUserRepositoryTest extends AbstractJpaRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	public void shouldSave() {
		// given
		User user = createBuilder().build();

		// when
		User savedUser = userRepository.save(user);
		flushAndClear();

		savedUser = userRepository.findOne(savedUser.getEntityId());

		// then
		EntityAssert.assertThat(savedUser).isManaged();

		assertThat(savedUser.getIdentifier()).isEqualTo(user.getIdentifier());
		assertThat(savedUser.getEmail()).isEqualTo(user.getEmail());
		assertThat(savedUser.getFirstname()).isEqualTo(user.getFirstname());
		assertThat(savedUser.getLastname()).isEqualTo(user.getLastname());
		assertThat(savedUser.getFullname()).isEqualTo(user.getFullname());
		assertThat(savedUser.isEnabled()).isEqualTo(user.isEnabled());
		EntitiesAssert.assertThat(savedUser.getGroups()).containsOnly(user.getGroups());
	}

	@Test(expectedExceptions = DataIntegrityViolationException.class)
	public void shouldNotSaveDuplicates() {
		// given
		UserIdentifier identifier = new UserIdentifier("duplicate");
		Builder builder = createBuilder().withIdentifier(identifier);

		User user1 = builder.build();
		User user2 = builder.build();

		// when
		userRepository.save(newArrayList(user1, user2));
	}

	@Test
	public void shouldFindByIdentifier() {
		// given
		UserIdentifier identifier = new UserIdentifier("identifier");

		User expectedUser = saveFlushAndClear(createBuilder().withIdentifier(identifier).build());

		// when
		User user = userRepository.findByIdentifier(identifier);

		// then
		EntityAssert.assertThat(user).isEqualTo(expectedUser);
	}

	private Builder createBuilder() {
		return new Builder().withIdentifier(new UserIdentifier("any user")).withEmail("any@domain.com")
				.withFirstname("any firstname").withLastname("any lastname").withFullname("any fullname")
				.withEnabled(Boolean.TRUE).withGroups(createPersistedGroup("group 1"), createPersistedGroup("group 2"));
	}

	private Group createPersistedGroup(String name) {
		return saveFlushAndClear(new Group.Builder().withName(name).build());
	}

}
