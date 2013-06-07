package example.security.domain;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import example.TestGroups;
import example.security.domain.User;
import example.security.domain.PasswordEncoder;

@Test(groups = TestGroups.UNIT)
@Listeners(MockitoTestNGListener.class)
public class UserTest {

	private static final String ANY_USERNAME = "any username";
	private static final String ANY_DISPLAY_NAME = "any display name";

	private static final String RAW_PASSWORD = "any password";
	private static final String PASSWORD_HASH = "any password hash";

	@Mock
	private PasswordEncoder passwordEncoder;

	public void disable() {
		// given
		User user = new User(ANY_USERNAME, ANY_DISPLAY_NAME);

		// when
		user.disable();

		// then
		assertThat(user.isEnabled()).isFalse();
	}

	public void enable() {
		// given
		User user = new User(ANY_USERNAME, ANY_DISPLAY_NAME);

		// when
		user.enable();

		// then
		assertThat(user.isEnabled()).isTrue();
	}

	public void updatePassword() {
		// given
		User user = new User(ANY_USERNAME, ANY_DISPLAY_NAME);

		// given
		when(passwordEncoder.encode(RAW_PASSWORD)).thenReturn(PASSWORD_HASH);

		// when
		user.updatePassword(passwordEncoder, RAW_PASSWORD);

		// then
		assertThat(user.getPassword()).isEqualTo(PASSWORD_HASH);
	}
}
