package example.domain.todo.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import example.domain.shared.bootstrap.BootstrapEvent;
import example.domain.shared.bootstrap.BootstrapOrder;
import example.domain.todo.domain.Todo;
import example.domain.todo.domain.TodoRepository;

@Component
public class TodoBootstrapListener implements ApplicationListener<BootstrapEvent>, Ordered {

	@Autowired
	private TodoRepository todoRepository;

	@Override
	public void onApplicationEvent(BootstrapEvent event) {
		if (!todosExist()) {
			todoRepository.save(Lists.newArrayList(new Todo("Remember the milk"), new Todo("Clean the carpet")));
			todoRepository.flush();
		}
	}

	@Override
	public int getOrder() {
		return BootstrapOrder.TODO_TODO;
	}

	public boolean todosExist() {
		return todoRepository.count() > 0;
	}

}
