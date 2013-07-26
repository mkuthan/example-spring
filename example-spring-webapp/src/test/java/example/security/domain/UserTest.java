package example.security.domain;

import static org.fest.assertions.Assertions.assertThat;

import org.testng.annotations.Test;

import example.TestGroups;
import example.security.domain.User.Builder;

@Test(groups = TestGroups.UNIT)
public class UserTest {

	private static final UserIdentifier ANY_IDENTIFIER = new UserIdentifier("any identifier");
	private static final String ANY_EMAIL = "any@domain.com";
	private static final String ANY_FIRSTNAME = "any firstname";
	private static final String ANY_LASTNAME = "any lastname";

	public void disable() {
		// given
		User user = createBuilder().build();

		// when
		user.disable();

		// then
		assertThat(user.isEnabled()).isFalse();
	}

	public void enable() {
		// given
		User user = createBuilder().build();

		// when
		user.enable();

		// then
		assertThat(user.isEnabled()).isTrue();
	}

	private Builder createBuilder() {
		return new Builder().withIdentifier(ANY_IDENTIFIER).withEmail(ANY_EMAIL).withFirstname(ANY_FIRSTNAME)
				.withLastname(ANY_LASTNAME);
	}
}
