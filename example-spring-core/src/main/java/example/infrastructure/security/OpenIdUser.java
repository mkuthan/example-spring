package example.infrastructure.security;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.openid.OpenIDAttribute;
import org.springframework.security.openid.OpenIDAuthenticationToken;

import example.domain.shared.security.AuthenticatedUser;

public class OpenIdUser implements UserDetails, AuthenticatedUser {

	private static final long serialVersionUID = 1L;

	private static final String UNUSED_PASSWORD = "N/A";

	private static final boolean ACCOUNT_NON_EXPIRED = true;
	private static final boolean CREDENTIALS_NON_EXPIRED = true;
	private static final boolean ACCOUNT_NOT_LOCKED = true;

	private String username;

	private String email;

	private String firstname;

	private String lastname;

	private String fullname;

	private Boolean enabled;

	private List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

	protected OpenIdUser(Builder builder) {
		this.username = checkNotNull(builder.username);
		this.email = checkNotNull(builder.email);
		this.firstname = checkNotNull(builder.firstname);
		this.lastname = checkNotNull(builder.lastname);

		if (builder.fullname != null) {
			this.fullname = builder.fullname;
		} else {
			this.fullname = String.format("%s %s", this.firstname, this.lastname);
		}

		if (builder.enabled != null) {
			this.enabled = builder.enabled;
		} else {
			this.enabled = Boolean.TRUE;
		}

		this.grantedAuthorities.addAll(builder.grantedAuthorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.unmodifiableCollection(grantedAuthorities);
	}

	@Override
	public String getPassword() {
		return UNUSED_PASSWORD;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return ACCOUNT_NON_EXPIRED;
	}

	@Override
	public boolean isAccountNonLocked() {
		return ACCOUNT_NOT_LOCKED;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return CREDENTIALS_NON_EXPIRED;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public String getFirstname() {
		return firstname;
	}

	@Override
	public String getLastname() {
		return lastname;
	}

	@Override
	public String getFullname() {
		return fullname;
	}

	public static class Builder {

		private String username;

		private String email;

		private String firstname;

		private String lastname;

		private String fullname;

		private Boolean enabled = true;

		private List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

		public Builder withAuthenticationToken(OpenIDAuthenticationToken token) {
			this.username = token.getIdentityUrl();

			for (OpenIDAttribute attribute : token.getAttributes()) {
				if (attribute.getName().equals("email")) {
					this.email = attribute.getValues().get(0);
				}

				if (attribute.getName().equals("firstname")) {
					this.firstname = attribute.getValues().get(0);
				}

				if (attribute.getName().equals("lastname")) {
					this.lastname = attribute.getValues().get(0);
				}

				if (attribute.getName().equals("fullname")) {
					this.fullname = attribute.getValues().get(0);
				}
			}

			return this;
		}

		public Builder withEnabled(Boolean enabled) {
			this.enabled = enabled;
			return this;
		}

		public Builder addGrantedAuthority(GrantedAuthority grantedAuthority) {
			this.grantedAuthorities.add(grantedAuthority);
			return this;
		}

		public OpenIdUser build() {
			return new OpenIdUser(this);
		}
	}

}
