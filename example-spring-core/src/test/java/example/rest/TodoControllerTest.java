package example.rest;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.testng.annotations.Test;

import example.domain.todo.application.TodoService;
import example.domain.todo.domain.Todo;

public class TodoControllerTest extends AbstractContollerTest {

	@Autowired
	private TodoService todoService;

	@Test
	public void shouldList() throws Exception {
		// given
		Todo todo1 = new Todo("todo1");
		Todo todo2 = new Todo("todo2");
		ArrayList<Todo> todos = newArrayList(todo1, todo2);

		when(todoService.findAll()).thenReturn(todos);

		// when & then
		perform(get(RequestMappings.TODOS).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.").value(hasSize(todos.size())))
				.andExpect(jsonPath("$.[0].title").value(equalTo(todo1.getTitle())))
				.andExpect(jsonPath("$.[1].title").value(equalTo(todo2.getTitle())));
	}

	@Test
	public void shouldAdd() throws Exception {
		// given
		String title = "title";

		// when & then
		perform(post(RequestMappings.TODOS).param("title", title).accept(MediaType.APPLICATION_JSON)).andExpect(
				status().isCreated());

		verify(todoService).add(eq(title));
	}

	@Test
	public void shouldGet() throws Exception {
		// given
		Long id = 1L;

		Todo todo = new Todo("todo");
		when(todoService.findOne(eq(id))).thenReturn(todo);

		// when & then
		perform(get(RequestMappings.TODOS + "/{id}", id).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.title").value(equalTo(todo.getTitle())));
	}

	@Test
	public void shouldDelete() throws Exception {
		// given
		Long id = 1L;

		// when & then
		perform(delete(RequestMappings.TODOS + "/{id}", id).accept(MediaType.APPLICATION_JSON)).andExpect(
				status().isNoContent());

		verify(todoService).delete(eq(id));
	}

	@Test
	public void shouldUpdateStatusToDone() throws Exception {
		// given
		Long id = 1L;

		// when & then
		perform(put(RequestMappings.TODOS + "/{id}/done", id).accept(MediaType.APPLICATION_JSON)).andExpect(
				status().isNoContent());

		verify(todoService).done(eq(id));
	}

	@Test
	public void shouldUpdateTitle() throws Exception {
		// given
		Long id = 1L;
		String title = "title";

		// when & then
		perform(put(RequestMappings.TODOS + "/{id}/title", id).param("title", title).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());

		verify(todoService).updateTitle(eq(id), eq(title));
	}

}
