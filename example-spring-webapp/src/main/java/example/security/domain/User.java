package example.security.domain;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import example.shared.ddd.AbstractAggregateEntity;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends AbstractAggregateEntity {

	@Embedded
	private UserIdentifier identifier;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String firstname;

	@Column(nullable = false)
	private String lastname;

	@Column(nullable = false)
	private String fullname;

	@Column(nullable = false)
	private Boolean enabled = Boolean.TRUE;

	protected User() {
	}

	protected User(Builder builder) {
		this.identifier = checkNotNull(builder.identifier);
		this.email = checkNotNull(builder.email);
		this.firstname = checkNotNull(builder.firstname);
		this.lastname = checkNotNull(builder.lastname);

		if (builder.fullname != null) {
			this.fullname = builder.fullname;
		} else {
			this.fullname = String.format("%s %s", firstname, lastname);
		}
	}

	public UserIdentifier getIdentifier() {
		return identifier;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getDisplayName() {
		return String.format("%s %s", getFirstname(), getLastname());
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

	public static class Builder {

		private UserIdentifier identifier;

		private String email;

		private String firstname;

		private String lastname;

		private String fullname;

		public Builder withIdentifier(UserIdentifier identifier) {
			this.identifier = identifier;
			return this;
		}

		public Builder withEmail(String email) {
			this.email = email;
			return this;
		}

		public Builder withFirstname(String firstname) {
			this.firstname = firstname;
			return this;
		}

		public Builder withLastname(String lastname) {
			this.lastname = lastname;
			return this;
		}

		public Builder withFullname(String fullname) {
			this.fullname = fullname;
			return this;
		}

		public User build() {
			return new User(this);
		}
	}

}
