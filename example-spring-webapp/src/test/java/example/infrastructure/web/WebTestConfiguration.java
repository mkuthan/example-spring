package example.infrastructure.web;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import example.infrastructure.jpa.TestRepository;
import example.shared.security.AuthenticatedUserDetailsProvider;
import example.todo.application.TodoService;
import example.todo.domain.TodoRepository;

@Configuration
public class WebTestConfiguration {

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
