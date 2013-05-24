package example.web.example.todo;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.testng.annotations.Test;

import example.application.TodoService;
import example.domain.example.todo.Todo;
import example.domain.example.todo.TodoRepository;
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
				.andExpect(jsonPath("$.[1].title").value(equalTo(todo2.getTitle()))).andDo(print());
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
		perform(post(RequestMappings.TODO, title).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

		verify(todoService).add(eq(title));
	}

	@Test
	public void shouldDelete() throws Exception {
		// given
		Long id = 1L;

		// when & then
		perform(delete(RequestMappings.TODO + "/{id}", id).accept(MediaType.APPLICATION_JSON)).andExpect(
				status().isNoContent());

		verify(todoRepository).delete(eq(id));
	}

}
