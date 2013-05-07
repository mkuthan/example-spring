package example.domain.shared.security;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import example.TestGroups;
import example.domain.shared.security.Account;
import example.domain.shared.security.PasswordEncoder;

@Test(groups = TestGroups.UNIT)
@Listeners(MockitoTestNGListener.class)
public class AccountTest {

	private static final String ANY_USERNAME = "ANY USERNAME";
	
	private static final String RAW_PASSWORD = "ANY PASSWORD";
	private static final String PASSWORD_HASH = "ANY PASSWORD HASH";

	@Mock
	private PasswordEncoder passwordEncoder;

	@InjectMocks
	private Account account = new Account(ANY_USERNAME);

	public void disable() {
		// when
		account.disable();

		// then
		assertThat(account.isEnabled()).isFalse();
	}

	public void enable() {
		// when
		account.enable();

		// then
		assertThat(account.isEnabled()).isTrue();
	}

	public void updatePassword() {
		// given
		when(passwordEncoder.encode(RAW_PASSWORD)).thenReturn(PASSWORD_HASH);

		// when
		account.updatePassword(RAW_PASSWORD);

		// then
		assertThat(account.getPassword()).isEqualTo(PASSWORD_HASH);
	}
}
