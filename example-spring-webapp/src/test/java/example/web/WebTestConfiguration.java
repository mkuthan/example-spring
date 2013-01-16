package example.web;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import example.domain.example.ExampleRepository;

@Configuration
public class WebTestConfiguration {

	@Bean
	public ExampleRepository exampleRepository() {
		return Mockito.mock(ExampleRepository.class);
	}

}
