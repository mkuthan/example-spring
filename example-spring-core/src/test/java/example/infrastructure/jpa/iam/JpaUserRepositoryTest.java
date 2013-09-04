package example.infrastructure.jpa.iam;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static org.fest.assertions.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.testng.annotations.Test;

import example.Assertions;
import example.domain.iam.model.RoleIdentifier;
import example.domain.iam.model.User;
import example.domain.iam.model.UserIdentifier;
import example.domain.iam.model.UserRepository;
import example.domain.iam.model.User.Builder;
import example.infrastructure.jpa.AbstractJpaRepositoryTest;

public class JpaUserRepositoryTest extends AbstractJpaRepositoryTest {

	private static final UserIdentifier ANY_IDENTIFIER = new UserIdentifier("any user");
	private static final String ANY_EMAIL = "any@domain.com";
	private static final String ANY_FIRSTNAME = "any firstname";
	private static final String ANY_LASTNAME = "any lastname";
	private static final String ANY_FULLNAME = "any fullname";
	private static final Boolean ANY_ENABLED = Boolean.TRUE;
	private static final RoleIdentifier ANY_ROLE = new RoleIdentifier("any role");

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
		assertThat(savedUser.isManaged()).isTrue();

		assertThat(savedUser.getIdentifier()).isEqualTo(ANY_IDENTIFIER);
		assertThat(savedUser.getEmail()).isEqualTo(ANY_EMAIL);
		assertThat(savedUser.getFirstname()).isEqualTo(ANY_FIRSTNAME);
		assertThat(savedUser.getLastname()).isEqualTo(ANY_LASTNAME);
		assertThat(savedUser.getFullname()).isEqualTo(ANY_FULLNAME);
		assertThat(savedUser.isEnabled()).isEqualTo(ANY_ENABLED);
		assertThat(savedUser.getRoles()).isEqualTo(newHashSet(ANY_ROLE));
	}

	@Test(expectedExceptions = DataIntegrityViolationException.class)
	public void shouldNotSaveDuplicates() {
		// given
		UserIdentifier identifier = new UserIdentifier("duplicate");

		User user1 = createBuilder().withIdentifier(identifier).build();
		User user2 = createBuilder().withIdentifier(identifier).build();

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
		Assertions.assertThat(user).hasSameIdentity(expectedUser);
	}

	private Builder createBuilder() {
		return new Builder().withIdentifier(ANY_IDENTIFIER).withEmail(ANY_EMAIL).withFirstname(ANY_FIRSTNAME)
				.withLastname(ANY_LASTNAME).withFullname(ANY_FULLNAME).withEnabled(ANY_ENABLED).addRole(ANY_ROLE);
	}

}
