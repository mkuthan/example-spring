package example.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import example.shared.security.Account;
import example.shared.security.AccountProvider;
import example.shared.security.AccountRepository;

@Component
public class DefaultAccountProvider implements AccountProvider {

	@Autowired
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
