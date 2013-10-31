package example.infrastructure.jpa;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import example.domain.shared.audit.Auditor;

@Configuration
public class JpaTestConfiguration {

	@Bean
	public Auditor auditor() {
		return Mockito.mock(Auditor.class);
	}

}
