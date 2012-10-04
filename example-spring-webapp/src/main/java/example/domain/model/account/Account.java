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
	this.password = "";
	this.enabled = Boolean.TRUE;
    }

    public void disable() {
	this.enabled = Boolean.FALSE;
    }

    public void enable() {
	this.enabled = Boolean.TRUE;
    }

    public Boolean isEnabled() {
	return this.enabled;
    }

    public String getUsername() {
	return this.username;
    }

    public String getPassword() {
	return this.password;
    }

    public void updatePassword(String rawPassword) {
	checkNotNull(rawPassword);

	this.password = this.passwordEncodingService.encode(rawPassword);
    }

    @Override
    public String toString() {
	return Objects.toStringHelper(this).addValue(this.username).toString();
    }

    protected PasswordEncodingService getPasswordEncodingService() {
	return this.passwordEncodingService;
    }

    protected void setPasswordEncodingService(PasswordEncodingService passwordEncodingService) {
	this.passwordEncodingService = passwordEncodingService;
    }

}
