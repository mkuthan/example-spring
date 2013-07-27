package example.infrastructure.jpa;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import example.shared.date.DateTimeProvider;
import example.shared.security.AuthenticatedUserDetailsProvider;

@Configuration
public class JpaTestConfiguration {

	@Bean
	public AuthenticatedUserDetailsProvider userAuthenticatedUserDetailsProvider() {
		return Mockito.mock(AuthenticatedUserDetailsProvider.class);
	}

	@Bean
	public DateTimeProvider dateTimeProvider() {
		return Mockito.mock(DateTimeProvider.class);
	}

}
