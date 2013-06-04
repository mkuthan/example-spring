package example.infrastructure.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import example.shared.security.Account;
import example.shared.security.AccountProvider;

@Configuration
public class WebConfiguration {

	@Autowired
	private AccountProvider accountProvider;

	@Bean
	@Scope("session")
	public Account loggedUser() {
		return accountProvider.authenticated();
	}

}
