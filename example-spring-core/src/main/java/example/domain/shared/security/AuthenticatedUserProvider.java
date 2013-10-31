package example.domain.shared.security;

import com.google.common.base.Optional;

public interface AuthenticatedUserProvider {

	Optional<AuthenticatedUser> authenticatedUser();

}
