package example.domain.iam.model;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Cacheable;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import example.domain.shared.ddd.AbstractAggregateEntity;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends AbstractAggregateEntity {

	@Embedded
	@AttributeOverride(name = UserIdentifier.IDENTIFIER_PROPERTY, column = @Column(nullable = false, unique = true))
	private UserIdentifier identifier;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String firstname;

	@Column(nullable = false)
	private String lastname;

	@Column(nullable = false)
	private String fullname;

	@Column(nullable = false)
	private Boolean enabled;

	@CollectionTable
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<RoleIdentifier> roles = new HashSet<>();

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

		if (builder.enabled != null) {
			this.enabled = builder.enabled;
		} else {
			this.enabled = Boolean.TRUE;
		}

		this.roles.addAll(builder.roles);
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

	public String getFullname() {
		return fullname;
	}

	public void disable() {
		checkState(isEnabled());
		enabled = Boolean.FALSE;
	}

	public void enable() {
		checkState(isDisabled());
		enabled = Boolean.TRUE;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public Boolean isDisabled() {
		return !enabled;
	}

	public Set<RoleIdentifier> getRoles() {
		return Collections.unmodifiableSet(roles);
	}

	public static class Builder {

		private UserIdentifier identifier;

		private String email;

		private String firstname;

		private String lastname;

		private String fullname;

		private Boolean enabled;

		private Set<RoleIdentifier> roles = new HashSet<>();

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

		public Builder withEnabled(Boolean enabled) {
			this.enabled = enabled;
			return this;
		}

		public Builder addRole(RoleIdentifier role) {
			this.roles.add(role);
			return this;
		}

		public Builder addRoles(Set<RoleIdentifier> roles) {
			this.roles.addAll(roles);
			return this;
		}

		public User build() {
			return new User(this);
		}

	}

}
