package example.domain.model.account;

import static com.google.common.base.Preconditions.*;

import javax.persistence.Column;
import javax.persistence.Transient;

import com.google.common.base.Objects;

import example.domain.service.PasswordEncodingService;
import example.domain.shared.AbstractAggregateEntity;
import example.domain.shared.DomainEntity;

@DomainEntity
public class Account extends AbstractAggregateEntity<Account> {

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private Boolean enabled;

	@Transient
	private PasswordEncodingService passwordEncodingService;

	protected Account() {
	}

	public Account(String username) {
		this.username = checkNotNull(username);
		password = "";
		enabled = Boolean.TRUE;
	}

	public void disable() {
		enabled = Boolean.FALSE;
	}

	public void enable() {
		enabled = Boolean.TRUE;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void updatePassword(String rawPassword) {
		checkNotNull(rawPassword);

		password = passwordEncodingService.encode(rawPassword);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).addValue(username).toString();
	}

	protected PasswordEncodingService getPasswordEncodingService() {
		return passwordEncodingService;
	}

	protected void setPasswordEncodingService(PasswordEncodingService passwordEncodingService) {
		this.passwordEncodingService = passwordEncodingService;
	}

}
