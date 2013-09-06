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

@Controller
@RequestMapping(value = RequestMappings.TODOS)
public class TodoController {

	@Autowired
	private TodoService todoService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	List<Todo> list() {
		System.out.println("DUPA");
		return todoService.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void add(String title) {
		todoService.add(title);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	Todo get(@PathVariable("id") Long id) {
		return todoService.findOne(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		todoService.delete(id);
	}

	@RequestMapping(value = "/{id}/title", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateTitle(@PathVariable("id") Long id, String title) {
		todoService.updateTitle(id, title);
	}

	@RequestMapping(value = "/{id}/done", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void done(@PathVariable("id") Long id) {
		todoService.done(id);
	}

}
