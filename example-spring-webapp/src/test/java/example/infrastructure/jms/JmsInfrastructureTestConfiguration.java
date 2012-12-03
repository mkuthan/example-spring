package example.infrastructure.jms;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JmsInfrastructureTestConfiguration {

	@Bean
	public JmsTestListener testListener() {
		return Mockito.mock(JmsTestListener.class);
	}
}
