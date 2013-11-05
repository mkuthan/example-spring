package example.infrastructure.jpa.todo;

import static org.fest.assertions.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;

import example.domain.todo.domain.Todo;
import example.domain.todo.domain.TodoRepository;
import example.infrastructure.jpa.AbstractJpaRepositoryTest;

public class JpaTodoRepositoryTest extends AbstractJpaRepositoryTest {

	@Autowired
	private TodoRepository todoRepository;

	public void shouldSaveTodo() {
		// given
		Todo todo = new Todo("any title");

		// when
		Todo savedTodo = todoRepository.save(todo);
		flushAndClear();
		savedTodo = todoRepository.load(savedTodo.getEntityId());

		// then
		assertThat(savedTodo.isManaged()).isTrue();
		assertThat(savedTodo.getStatus()).isEqualTo(Todo.DEFAULT_STATUS);
		assertThat(savedTodo.getTitle()).isEqualTo(todo.getTitle());
	}
}
