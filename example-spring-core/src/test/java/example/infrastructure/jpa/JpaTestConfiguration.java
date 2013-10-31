package example.infrastructure.jpa;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import example.domain.shared.date.DateTimeProvider;
import example.domain.shared.security.AuthenticatedUserProvider;

@Configuration
public class JpaTestConfiguration {

	@Bean
	public AuthenticatedUserProvider userAuthenticatedUserDetailsProvider() {
		return Mockito.mock(AuthenticatedUserProvider.class);
	}

	@Bean
	public DateTimeProvider dateTimeProvider() {
		return Mockito.mock(DateTimeProvider.class);
	}

}
