package example.security.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import example.security.domain.UserProvider;

@Configuration
public class WebConfiguration {

	@Autowired
	private UserProvider userProvider;

	@Bean
	@Scope("session")
	public String loggedUserDisplayName() {
		return userProvider.authenticated().getDisplayName();
	}

}
