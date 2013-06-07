package example.infrastructure.jpa;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import example.security.domain.UserProvider;
import example.shared.date.DateTimeProvider;

@Configuration
public class JpaTestConfiguration {

	@Bean
	public UserProvider userProvider() {
		return Mockito.mock(UserProvider.class);
	}

	@Bean
	public DateTimeProvider dateTimeProvider() {
		return Mockito.mock(DateTimeProvider.class);
	}

}
