package example.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import example.domain.todo.application.TodoService;
import example.domain.todo.domain.Todo;
import example.domain.todo.domain.TodoRepository;

@Controller
@RequestMapping
public class TodoController {

	@Autowired
	private TodoRepository todoRepository;

	@Autowired
	private TodoService todoService;

	@RequestMapping(value = "/todos", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	List<Todo> list() {
		return todoRepository.findAll();
	}

	@RequestMapping(value = "/todos/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	Todo get(@PathVariable("id") Long id) {
		return todoRepository.findOne(id);
	}

	@RequestMapping(value = "/todos", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void add(String title) {
		todoService.add(title);
	}

	@RequestMapping(value = "/todos/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		todoService.delete(id);
	}

	@RequestMapping(value = "/todos/{id}/title", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateTitle(@PathVariable("id") Long id, String title) {
		todoService.updateTitle(id, title);
	}

	@RequestMapping(value = "/todos/{id}/done", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void done(@PathVariable("id") Long id) {
		todoService.done(id);
	}

}
