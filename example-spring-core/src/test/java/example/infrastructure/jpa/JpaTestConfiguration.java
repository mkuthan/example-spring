package example.infrastructure.jpa;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import example.domain.shared.ddd.EventPublisher;

@Configuration
public class JpaTestConfiguration {

	@Bean
	public EventPublisher eventPublisher() {
		return Mockito.mock(EventPublisher.class);
	}

}
