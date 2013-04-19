package example.infrastructure.events;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;

public abstract class AbstractEventHandler implements EventHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractEventHandler.class);

	private final Class<?> eventType;
	private final String beanName;
	private final Method method;
	private final BeanFactory beanFactory;

	public AbstractEventHandler(Class<?> eventType, String beanName, Method method, BeanFactory beanFactory) {
		this.eventType = checkNotNull(eventType);
		this.beanName = checkNotNull(beanName);
		this.method = checkNotNull(method);
		this.beanFactory = checkNotNull(beanFactory);
	}

	public boolean canHandle(Object event) {
		checkNotNull(event);

		return eventType.isAssignableFrom(event.getClass());
	}

	protected void invokeBean(Object event) {
		checkNotNull(event);

		try {
			Object bean = beanFactory.getBean(beanName);
			method.invoke(bean, event);
		} catch (Exception e) {
			LOGGER.error("Bean invocation error.", e);
		}
	}

}