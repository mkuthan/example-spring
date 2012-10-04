package example.domain.model.account;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.testng.MockitoTestNGListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import example.domain.service.PasswordEncodingService;

@Test
@Listeners(MockitoTestNGListener.class)
public class AccountTest {

    static final String RAW_PASSWORD = "ANY PASSWORD";
    static final String PASSWORD_HASH = "ANY PASSWORD HASH";

    @Mock
    PasswordEncodingService passwordEncodingService;

    @InjectMocks
    Account account = new Account();

    public void disable() {
	this.account.disable();

	assertThat(this.account.isEnabled()).isFalse();
    }

    public void enable() {
	this.account.enable();

	assertThat(this.account.isEnabled()).isTrue();
    }

    public void updatePassword() {
	when(this.passwordEncodingService.encode(RAW_PASSWORD)).thenReturn(PASSWORD_HASH);

	this.account.updatePassword(RAW_PASSWORD);

	assertThat(this.account.getPassword()).isEqualTo(PASSWORD_HASH);
    }
}
