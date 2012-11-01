package example.infrastructure.jms;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import example.infrastructure.jms.JmsInfrastructureTest.TestListenerDelegate;

@Configuration
public class JmsInfrastructureTestConfiguration {
	
	@Bean
	public TestListenerDelegate testListenerDelegate() {
		return Mockito.mock(TestListenerDelegate.class);
	}
}
