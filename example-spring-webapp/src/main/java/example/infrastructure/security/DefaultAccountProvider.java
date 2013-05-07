package example.infrastructure.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import example.domain.shared.security.Account;
import example.domain.shared.security.AccountProvider;
import example.domain.shared.security.AccountRepository;

public class DefaultAccountProvider implements AccountProvider {

	private AccountRepository accountRepository;

	@Override
	public Account authenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return null;
		}

		return accountRepository.findByUsername(authentication.getName());
	}

}
