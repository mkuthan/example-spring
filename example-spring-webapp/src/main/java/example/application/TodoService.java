package example.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import example.ddd.AbstractApplicationService;
import example.domain.example.todo.Todo;
import example.domain.example.todo.TodoRepository;

@Component
public class TodoService extends AbstractApplicationService {

	@Autowired
	private TodoRepository todoRepository;

	@Transactional
	public void add(String title) {
		Todo todo = new Todo("title");
		todoRepository.saveAndFlush(todo);
	}

	@Transactional
	public void updateTitle(Long id, String title) {
		Todo todo = todoRepository.findOne(id);
		todo.updateTitle(title);
		todoRepository.saveAndFlush(todo);
	}

	@Transactional
	public void done(Long id) {
		Todo todo = todoRepository.findOne(id);
		todo.done();
		todoRepository.saveAndFlush(todo);
	}

}
