package example.bootstrap.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import example.bootstrap.BootstrapEvent;
import example.bootstrap.BootstrapOrder;
import example.domain.example.todo.Todo;
import example.domain.example.todo.TodoRepository;

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
		return BootstrapOrder.TODO;
	}

	public boolean todosExist() {
		return todoRepository.count() > 0;
	}

}
