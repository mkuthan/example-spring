package example.domain.todo.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import example.domain.shared.ddd.AbstractApplicationService;
import example.domain.shared.ddd.ApplicationService;
import example.domain.todo.domain.Todo;
import example.domain.todo.domain.TodoFactory;
import example.domain.todo.domain.TodoRepository;

@ApplicationService
public class TodoService extends AbstractApplicationService {

	@Autowired
	private TodoRepository todoRepository;

	@Autowired
	private TodoFactory todoFactory;

	@Transactional(readOnly = true)
	public Todo findOne(Long id) {
		return todoRepository.load(id);
	}

	@Transactional(readOnly = true)
	public List<Todo> findAll() {
		return todoRepository.loadAll();
	}

	@Transactional
	public void add(String title) {
		Todo todo = todoFactory.create(title);
		todoRepository.save(todo);
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
		todoRepository.save(todo);
	}

	@Transactional
	public void done(Long id) {
		Todo todo = findOne(id);
		todo.done();
		todoRepository.save(todo);
	}

}
