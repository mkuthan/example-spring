package example.domain.todo;

import static org.fest.assertions.Assertions.assertThat;

import org.testng.annotations.Test;

import example.TestGroups;
import example.domain.todo.Todo;
import example.domain.todo.TodoIllegalStateException;
import example.domain.todo.Todo.Status;

@Test(groups = TestGroups.UNIT)
public class TodoStatusTest {

	private static final String ANY_TITLE = "any title";

	@Test
	public void shouldUpdateTitleWhenActive() {
		// given
		Status status = Status.ACTIVE;
		Todo todo = new Todo(ANY_TITLE);

		// when
		status.updateTitle(todo, "title");

		// then
		assertThat(todo.isActive()).isTrue();
	}

	@Test
	public void shouldDoneWhenActive() {
		// given
		Status status = Status.ACTIVE;
		Todo todo = new Todo(ANY_TITLE);

		// when
		status.done(todo);

		// then
		assertThat(todo.isDone()).isTrue();
	}

	@Test(expectedExceptions = TodoIllegalStateException.class)
	public void shouldNotUpdateTitleWhenDone() {
		// given
		Status status = Status.DONE;

		Todo todo = new Todo(ANY_TITLE);

		// when
		status.updateTitle(todo, "title");
	}

	@Test(expectedExceptions = TodoIllegalStateException.class)
	public void shouldNotDoneWhenDone() {
		// given
		Status status = Status.DONE;

		Todo todo = new Todo(ANY_TITLE);

		// when
		status.done(todo);
	}

}
