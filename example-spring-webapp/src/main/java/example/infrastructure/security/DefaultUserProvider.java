package example.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import example.security.domain.User;
import example.security.domain.UserProvider;
import example.security.domain.UserRepository;

@Component
public class DefaultUserProvider implements UserProvider {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User authenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return null;
		}

		return userRepository.findByUsername(authentication.getName());
	}

}
