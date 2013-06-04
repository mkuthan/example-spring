package example.web;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import example.application.TodoService;
import example.ddd.EventPublisher;
import example.domain.example.ExampleRepository;
import example.domain.todo.TodoRepository;

@Configuration
public class WebTestConfiguration {

	@Bean
	public EventPublisher EventPublisher() {
		return Mockito.mock(EventPublisher.class);
	}

	@Bean
	public ExampleRepository exampleRepository() {
		return Mockito.mock(ExampleRepository.class);
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
