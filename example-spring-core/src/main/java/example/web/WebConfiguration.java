package example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import example.domain.shared.security.AuthenticatedUserProvider;

@Configuration
public class WebConfiguration {

	@Autowired
	private AuthenticatedUserProvider userProvider;

	@Bean
	@Scope("session")
	public String loggedUserDisplayName() {
		return userProvider.authenticated().getFullname();
	}

}
