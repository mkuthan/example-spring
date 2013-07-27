package example.iam.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import example.shared.security.AuthenticatedUserDetailsProvider;

@Configuration
public class WebConfiguration {

	@Autowired
	private AuthenticatedUserDetailsProvider userProvider;

	@Bean
	@Scope("session")
	public String loggedUserDisplayName() {
		return userProvider.authenticated().getFullname();
	}

}
