package example.infrastructure.events;

import java.lang.reflect.Method;

import org.springframework.beans.factory.BeanFactory;

public class SyncEventHandler extends AbstractEventHandler {

	public SyncEventHandler(Class<?> eventType, String beanName, Method method, BeanFactory beanFactory) {
		super(eventType, beanName, method, beanFactory);
	}

	public void handle(Object event) {
		invokeBean(event);
	}
}