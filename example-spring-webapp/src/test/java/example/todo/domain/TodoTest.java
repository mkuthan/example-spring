package example.todo.domain;

import static org.fest.assertions.Assertions.assertThat;

import org.testng.annotations.Test;

import example.TestGroups;

@Test(groups = TestGroups.UNIT)
public class TodoTest {

	private static final String ANY_TITLE = "any title";

	@Test
	public void shouldConstructTodo() {
		// given
		String title = "title";

		// when
		Todo todo = new Todo(title);

		// then
		assertThat(todo.isActive()).isTrue();
		assertThat(todo.isDone()).isFalse();

		assertThat(todo.getTitle()).isEqualTo(title);
	}

	@Test
	public void shouldUpdateTitle() {
		// given
		Todo todo = new Todo(ANY_TITLE);
		String title = "title";

		// when
		todo.updateTitle(title);

		// then
		assertThat(todo.getTitle()).isEqualTo(title);
	}

	@Test
	public void shouldDoneActiveTodo() {
		// given
		Todo todo = new Todo(ANY_TITLE);

		// when
		todo.done();

		// then
		assertThat(todo.isActive()).isFalse();
		assertThat(todo.isDone()).isTrue();
	}
}
