package example.domain.todo.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class TodoFactory {

	@Autowired
	private AutowireCapableBeanFactory beanFactory;

	public Todo create(String title) {
		Todo todo = new Todo();

		beanFactory.autowireBean(todo);

		return todo;
	}
}
