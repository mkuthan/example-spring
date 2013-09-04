package example.rest;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import example.domain.shared.security.AuthenticatedUserDetailsProvider;
import example.domain.todo.application.TodoService;
import example.domain.todo.domain.TodoRepository;
import example.infrastructure.jpa.TestRepository;

@Configuration
public class RestTestConfiguration {

	@Bean
	public AuthenticatedUserDetailsProvider userProvider() {
		return Mockito.mock(AuthenticatedUserDetailsProvider.class);
	}

	@Bean
	public TestRepository testRepository() {
		return Mockito.mock(TestRepository.class);
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
