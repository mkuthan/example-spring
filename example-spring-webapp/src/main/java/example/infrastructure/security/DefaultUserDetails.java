package example.infrastructure.security;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import example.security.domain.User;

public class DefaultUserDetails extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 1L;

	private static final String UNUSED_PASSWORD = "N/A";

	private static final boolean ACCOUNT_NON_EXPIRED = true;
	private static final boolean CREDENTIALS_NON_EXPIRED = true;
	private static final boolean ACCOUNT_NOT_LOCKED = true;

	private String displayName;

	public DefaultUserDetails(User user, List<GrantedAuthority> grantedAuthorities) {
		super(user.getIdentifier().getIdentifier(), UNUSED_PASSWORD, user.isEnabled(), ACCOUNT_NON_EXPIRED,
				CREDENTIALS_NON_EXPIRED, ACCOUNT_NOT_LOCKED, grantedAuthorities);

		this.displayName = user.getDisplayName();
	}

	public String getDisplayName() {
		return displayName;
	}

}
