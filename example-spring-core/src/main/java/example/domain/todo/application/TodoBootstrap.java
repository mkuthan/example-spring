package example.domain.todo.application;

import static com.google.common.collect.Lists.newArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import example.domain.shared.bootstrap.BootstrapEvent;
import example.domain.shared.bootstrap.BootstrapOrder;
import example.domain.todo.domain.TodoFactory;
import example.domain.todo.domain.TodoRepository;

@Component
public class TodoBootstrap implements ApplicationListener<BootstrapEvent>, Ordered {

	@Autowired
	private TodoRepository todoRepository;

	private TodoFactory todoFactory;

	@Override
	public void onApplicationEvent(BootstrapEvent event) {
		if (!todosExist()) {
			todoRepository.saveAll(newArrayList(todoFactory.create("Remember the milk"),
					todoFactory.create("Clean the carpet")));
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
