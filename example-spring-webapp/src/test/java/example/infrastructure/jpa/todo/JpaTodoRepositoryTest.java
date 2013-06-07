package example.infrastructure.jpa.todo;

import static org.fest.assertions.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;

import example.infrastructure.jpa.AbstractJpaRepositoryTest;
import example.todo.domain.Todo;
import example.todo.domain.TodoRepository;

public class JpaTodoRepositoryTest extends AbstractJpaRepositoryTest {

	private static final String ANY_TITLE = "any title";

	@Autowired
	private TodoRepository todoRepository;

	public void shouldSaveTodo() {
		// given
		Todo todo = new Todo(ANY_TITLE);

		// when
		Todo savedTodo = todoRepository.save(todo);
		flushAndClear();
		savedTodo = todoRepository.findOne(savedTodo.getEntityId());

		// then
		assertThat(savedTodo.isManaged()).isTrue();
		assertThat(savedTodo.getStatus()).isEqualTo(Todo.DEFAULT_STATUS);
		assertThat(savedTodo.getTitle()).isEqualTo(ANY_TITLE);
	}

}
