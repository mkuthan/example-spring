package example.domain.todo.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.transaction.annotation.Transactional;

import example.domain.shared.ddd.AbstractApplicationService;
import example.domain.shared.ddd.ApplicationService;
import example.domain.todo.domain.Todo;
import example.domain.todo.domain.TodoRepository;

@ApplicationService
public class TodoService extends AbstractApplicationService {

	@Autowired
	private TodoRepository todoRepository;

	@Transactional(readOnly = true)
	public Todo findOne(Long id) {
		Todo todo = todoRepository.findOne(id);
		if (todo == null) {
			throw new ObjectRetrievalFailureException(Todo.class, id);
		}
		return todo;
	}

	@Transactional(readOnly = true)
	public List<Todo> findAll() {
		return todoRepository.findAll();
	}

	@Transactional
	public void add(String title) {
		Todo todo = new Todo(title);
		todoRepository.saveAndFlush(todo);
	}

	@Transactional
	public void delete(Long id) {
		Todo todo = findOne(id);
		todoRepository.delete(todo);
	}

	@Transactional
	public void updateTitle(Long id, String title) {
		Todo todo = findOne(id);
		todo.updateTitle(title);
		todoRepository.saveAndFlush(todo);
	}

	@Transactional
	public void done(Long id) {
		Todo todo = findOne(id);
		todo.done();
		todoRepository.saveAndFlush(todo);
	}

}
