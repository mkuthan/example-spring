package example.infrastructure.events;

import java.lang.reflect.Method;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.task.TaskExecutor;

import example.ddd.EventListener;

public class EventListenerBeanPostProcessor implements BeanPostProcessor, BeanFactoryAware {

	private BeanFactory beanFactory;

	private DefaultEventPublisher eventPublisher;

	private TaskExecutor taskExecutor;

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		for (Method method : bean.getClass().getMethods()) {
			EventListener listenerAnnotation = method.getAnnotation(EventListener.class);

			if (listenerAnnotation == null) {
				continue;
			}

			Class<?> eventType = method.getParameterTypes()[0];

			EventHandler handler = null;
			if (listenerAnnotation.async()) {
				handler = new AsyncEventHandler(eventType, beanName, method, beanFactory, taskExecutor);
			} else {
				handler = new SyncEventHandler(eventType, beanName, method, beanFactory);
			}

			eventPublisher.registerEventHandler(handler);
		}

		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		// do nothing
		return bean;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}

	public void setEventPublisher(DefaultEventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}

	public void setTaskExecutor(TaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

}