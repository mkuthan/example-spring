package example.web;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import example.domain.shared.ddd.EventPublisher;
import example.domain.shared.security.AuthenticatedUserProvider;
import example.domain.todo.application.TodoService;
import example.domain.todo.domain.TodoFactory;
import example.domain.todo.domain.TodoRepository;

@Configuration
public class WebTestConfiguration {

	@Bean
	public AuthenticatedUserProvider userProvider() {
		return Mockito.mock(AuthenticatedUserProvider.class);
	}

	@Bean
	public EventPublisher eventPublisher() {
		return Mockito.mock(EventPublisher.class);
	}

	@Bean
	public TodoRepository todoRepository() {
		return Mockito.mock(TodoRepository.class);
	}

	@Bean
	public TodoService todoService() {
		return Mockito.mock(TodoService.class);
	}

	@Bean
	public TodoFactory todoFactory() {
		return Mockito.mock(TodoFactory.class);
	}
}
