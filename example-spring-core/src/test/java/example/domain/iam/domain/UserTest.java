package example.domain.iam.domain;

import static com.google.common.collect.Sets.newHashSet;
import static org.fest.assertions.Assertions.assertThat;

import java.util.Set;

import org.testng.annotations.Test;

import example.TestGroups;
import example.domain.iam.model.RoleIdentifier;
import example.domain.iam.model.User;
import example.domain.iam.model.UserIdentifier;
import example.domain.iam.model.User.Builder;

@Test(groups = TestGroups.UNIT)
public class UserTest {

	private static final UserIdentifier ANY_IDENTIFIER = new UserIdentifier("any identifier");
	private static final String ANY_EMAIL = "any@domain.com";
	private static final String ANY_FIRSTNAME = "any firstname";
	private static final String ANY_LASTNAME = "any lastname";

	public void shouldConstruct() {
		// given
		UserIdentifier identifier = new UserIdentifier("id");
		String email = "email@domain.com";
		String firstname = "John";
		String lastname = "White";
		String fullname = "John White";
		Boolean enabled = Boolean.TRUE;
		Set<RoleIdentifier> roles = newHashSet(new RoleIdentifier("role1"), new RoleIdentifier("role2"));

		// when
		User user = createBuilder().withIdentifier(identifier).withEmail(email).withFirstname(firstname)
				.withLastname(lastname).withFullname(fullname).withEnabled(enabled).addRoles(roles).build();

		// then
		assertThat(user.getIdentifier()).isEqualTo(identifier);
		assertThat(user.getEmail()).isEqualTo(email);
		assertThat(user.getFirstname()).isEqualTo(firstname);
		assertThat(user.getLastname()).isEqualTo(lastname);
		assertThat(user.getFullname()).isEqualTo(fullname);
		assertThat(user.isEnabled()).isEqualTo(enabled);
		assertThat(user.getRoles()).isEqualTo(roles);
	}

	public void shouldDisable() {
		// given
		User user = createBuilder().withEnabled(Boolean.TRUE).build();

		// when
		user.disable();

		// then
		assertThat(user.isDisabled()).isTrue();
	}

	@Test(expectedExceptions = IllegalStateException.class)
	public void shouldNotDisableWhenDisabled() {
		// given
		User user = createBuilder().withEnabled(Boolean.FALSE).build();

		// when
		user.disable();
	}

	public void shouldEnable() {
		// given
		User user = createBuilder().withEnabled(Boolean.FALSE).build();

		// when
		user.enable();

		// then
		assertThat(user.isEnabled()).isTrue();
	}

	@Test(expectedExceptions = IllegalStateException.class)
	public void shouldNotEnableWhenEnabled() {
		// given
		User user = createBuilder().withEnabled(Boolean.TRUE).build();

		// when & then
		user.enable();
	}

	private Builder createBuilder() {
		return new Builder().withIdentifier(ANY_IDENTIFIER).withEmail(ANY_EMAIL).withFirstname(ANY_FIRSTNAME)
				.withLastname(ANY_LASTNAME);
	}
}
