package example.infrastructure.jpa.iam;

import static com.google.common.collect.Lists.newArrayList;
import static org.fest.assertions.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.testng.annotations.Test;

import example.Assertions;
import example.iam.domain.Role;
import example.iam.domain.RoleIdentifier;
import example.iam.domain.RoleRepository;
import example.infrastructure.jpa.AbstractJpaRepositoryTest;

public class JpaRoleRepositoryTest extends AbstractJpaRepositoryTest {

	private static final RoleIdentifier ANY_IDENTIFIER = new RoleIdentifier("any role");
	private static final String ANY_NAME = "any role name";

	@Autowired
	private RoleRepository roleRepository;

	public void shouldSave() {
		// given
		Role role = new Role(ANY_IDENTIFIER, ANY_NAME);

		// when
		Role savedRole = roleRepository.save(role);
		flushAndClear();

		savedRole = roleRepository.findOne(savedRole.getEntityId());

		// then
		assertThat(savedRole.isManaged()).isTrue();

		assertThat(savedRole.getIdentifier()).isEqualTo(ANY_IDENTIFIER);
		assertThat(savedRole.getName()).isEqualTo(ANY_NAME);
	}

	@Test(expectedExceptions = DataIntegrityViolationException.class)
	public void shouldNotSaveDuplicates() {
		// given
		RoleIdentifier identifier = new RoleIdentifier("duplicate");

		Role role1 = new Role(identifier, ANY_NAME);
		Role role2 = new Role(identifier, ANY_NAME);

		// when
		roleRepository.save(newArrayList(role1, role2));
	}

	@Test
	public void shouldFindByIdentifier() {
		// given
		RoleIdentifier identifier = new RoleIdentifier("identifier");

		Role expectedRole = saveFlushAndClear(new Role(identifier, ANY_NAME));

		// when
		Role role = roleRepository.findByIdentifier(identifier);

		// then
		Assertions.assertThat(role).hasSameIdentity(expectedRole);
	}

}
