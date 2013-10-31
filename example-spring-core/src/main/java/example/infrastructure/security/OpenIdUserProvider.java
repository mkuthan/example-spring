package example.infrastructure.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;

import example.domain.shared.security.AuthenticatedUser;
import example.domain.shared.security.AuthenticatedUserProvider;

@Component
public class OpenIdUserProvider implements AuthenticatedUserProvider {

	@Override
	public Optional<AuthenticatedUser> authenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return Optional.absent();
		}

		return Optional.of((AuthenticatedUser) authentication.getDetails());
	}

}
