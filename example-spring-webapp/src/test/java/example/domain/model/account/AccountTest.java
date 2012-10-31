package example.domain.model.account;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import example.TestGroups;
import example.domain.service.PasswordEncodingService;

@Test(groups = TestGroups.UNIT)
@Listeners(MockitoTestNGListener.class)
public class AccountTest {

	static final String RAW_PASSWORD = "ANY PASSWORD";
	static final String PASSWORD_HASH = "ANY PASSWORD HASH";

	@Mock
	PasswordEncodingService passwordEncodingService;

	@InjectMocks
	Account account = new Account();

	public void disable() {
		account.disable();

		assertThat(account.isEnabled()).isFalse();
	}

	public void enable() {
		account.enable();

		assertThat(account.isEnabled()).isTrue();
	}

	public void updatePassword() {
		when(passwordEncodingService.encode(RAW_PASSWORD)).thenReturn(PASSWORD_HASH);

		account.updatePassword(RAW_PASSWORD);

		assertThat(account.getPassword()).isEqualTo(PASSWORD_HASH);
	}
}
