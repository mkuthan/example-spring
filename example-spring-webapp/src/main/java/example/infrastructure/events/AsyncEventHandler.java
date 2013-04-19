package example.infrastructure.events;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Method;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.task.TaskExecutor;

public class AsyncEventHandler extends AbstractEventHandler {

	private TaskExecutor taskExecutor;

	public AsyncEventHandler(Class<?> eventType, String beanName, Method method, BeanFactory beanFactory,
			TaskExecutor taskExecutor) {
		super(eventType, beanName, method, beanFactory);
		this.taskExecutor = checkNotNull(taskExecutor);
	}

	public void handle(final Object event) {
		taskExecutor.execute(new Runnable() {
			public void run() {
				invokeBean(event);
			}
		});

	}
}