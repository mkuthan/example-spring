package example.domain.security;

import static com.google.common.base.Preconditions.*;

import javax.persistence.Column;
import javax.persistence.Transient;

import com.google.common.base.Objects;

import example.domain.shared.ddd.AbstractAggregateEntity;
import example.domain.shared.ddd.DomainEntity;

@DomainEntity
public class Account extends AbstractAggregateEntity {

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private Boolean enabled;

	@Transient
	private PasswordEncodeService passwordEncodeService;

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

		password = passwordEncodeService.encode(rawPassword);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).addValue(username).toString();
	}

	protected PasswordEncodeService getPasswordEncodingService() {
		return passwordEncodeService;
	}

	protected void setPasswordEncodingService(PasswordEncodeService passwordEncodeService) {
		this.passwordEncodeService = passwordEncodeService;
	}

}
