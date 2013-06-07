package example.security.domain;

import static com.google.common.base.Preconditions.*;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import example.shared.ddd.AbstractAggregateEntity;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends AbstractAggregateEntity {

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password = "";

	@Column(nullable = false)
	private Boolean enabled = Boolean.TRUE;

	@Column(nullable = false)
	private String displayName;

	protected User() {
	}

	public User(String username, String displayName) {
		this.username = checkNotNull(username);
		this.displayName = checkNotNull(displayName);
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

	public String getDisplayName() {
		return displayName;
	}

	public void updatePassword(PasswordEncoder passwordEncoder, String rawPassword) {
		checkNotNull(rawPassword);

		password = passwordEncoder.encode(rawPassword);
	}

}
