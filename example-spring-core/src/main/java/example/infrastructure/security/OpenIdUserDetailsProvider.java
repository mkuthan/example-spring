package example.infrastructure.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import example.domain.shared.security.AuthenticatedUserDetails;
import example.domain.shared.security.AuthenticatedUserDetailsProvider;

@Component
public class OpenIdUserDetailsProvider implements AuthenticatedUserDetailsProvider {

	@Override
	public AuthenticatedUserDetails authenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return null;
		}

		return (AuthenticatedUserDetails) authentication.getDetails();
	}

}
