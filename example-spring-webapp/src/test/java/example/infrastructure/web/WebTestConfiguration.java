package example.infrastructure.web;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import example.sample.domain.SampleRepository;
import example.shared.security.AccountProvider;
import example.todo.application.TodoService;
import example.todo.domain.TodoRepository;

@Configuration
public class WebTestConfiguration {

	@Bean
	public AccountProvider accountProvider() {
		return Mockito.mock(AccountProvider.class);
	}

	@Bean
	public SampleRepository sampleRepository() {
		return Mockito.mock(SampleRepository.class);
	}

	@Bean
	public TodoRepository todoRepository() {
		return Mockito.mock(TodoRepository.class);
	}

	@Bean
	public TodoService todoService() {
		return Mockito.mock(TodoService.class);
	}
}
