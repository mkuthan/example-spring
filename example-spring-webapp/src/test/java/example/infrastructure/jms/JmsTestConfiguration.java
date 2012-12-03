package example.infrastructure.jms;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JmsTestConfiguration {

	@Bean
	public JmsTestListener testQueueListener() {
		return Mockito.mock(JmsTestListener.class);
	}

	@Bean
	public JmsTestListener testTopicListener() {
		return Mockito.mock(JmsTestListener.class);
	}

	@Bean
	public JmsTestListener testPerformanceListener() {
		return Mockito.mock(JmsTestListener.class);
	}
}
