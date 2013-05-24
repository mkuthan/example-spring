package example.web.todo;

import static com.google.common.collect.Lists.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.testng.annotations.Test;

import example.application.TodoService;
import example.domain.todo.Todo;
import example.domain.todo.TodoRepository;
import example.web.AbstractContollerTest;
import example.web.RequestMappings;

public class TodoControllerTest extends AbstractContollerTest {

	@Autowired
	private TodoRepository todoRepository;

	@Autowired
	private TodoService todoService;

	@Test
	public void shouldList() throws Exception {
		// given
		Todo todo1 = new Todo("todo1");
		Todo todo2 = new Todo("todo2");
		ArrayList<Todo> todos = newArrayList(todo1, todo2);

		when(todoRepository.findAll()).thenReturn(todos);

		// when & then
		perform(get(RequestMappings.TODO).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.").value(hasSize(todos.size())))
				.andExpect(jsonPath("$.[0].title").value(equalTo(todo1.getTitle())))
				.andExpect(jsonPath("$.[1].title").value(equalTo(todo2.getTitle())));
	}

	@Test
	public void shouldGet() throws Exception {
		// given
		Long id = 1L;

		Todo todo = new Todo("todo");
		when(todoRepository.findOne(eq(id))).thenReturn(todo);

		// when & then
		perform(get(RequestMappings.TODO + "/{id}", id).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.title").value(equalTo(todo.getTitle())));
	}

	@Test
	public void shouldAdd() throws Exception {
		// given
		String title = "title";

		// when & then
		perform(post(RequestMappings.TODO).param("title", title).accept(MediaType.APPLICATION_JSON)).andExpect(
				status().isCreated());

		verify(todoService).add(eq(title));
	}

	@Test
	public void shouldDelete() throws Exception {
		// given
		Long id = 1L;

		// when & then
		perform(delete(RequestMappings.TODO + "/{id}", id).accept(MediaType.APPLICATION_JSON)).andExpect(
				status().isNoContent());

		verify(todoService).delete(eq(id));
	}

	@Test
	public void shouldUpdateStatusToDone() throws Exception {
		// given
		Long id = 1L;

		// when & then
		perform(put(RequestMappings.TODO + "/{id}/done", id).accept(MediaType.APPLICATION_JSON)).andExpect(
				status().isNoContent());

		verify(todoService).done(eq(id));
	}

	@Test
	public void shouldUpdateTitle() throws Exception {
		// given
		Long id = 1L;
		String title = "title";

		// when & then
		perform(put(RequestMappings.TODO + "/{id}/title", id).param("title", title).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());

		verify(todoService).updateTitle(eq(id), eq(title));
	}

}
