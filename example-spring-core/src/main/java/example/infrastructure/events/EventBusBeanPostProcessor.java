package example.infrastructure.events;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.EventBus;

@Component
public class EventBusBeanPostProcessor implements BeanPostProcessor {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private EventBus eventBus;

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (applicationContext.containsBean(beanName) && applicationContext.isSingleton(beanName)) {
			eventBus.register(bean);
		}
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		// do nothing
		return bean;
	}

}