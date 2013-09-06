package example.infrastructure.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import example.domain.shared.bootstrap.BootstrapEvent;

@Component
public class ApplicationBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		ApplicationContext applicationContext = event.getApplicationContext();

		if (isRootApplicationContext(applicationContext)) {
			event.getApplicationContext().publishEvent(new BootstrapEvent(applicationContext));
		}

	}

	private boolean isRootApplicationContext(ApplicationContext applicationContext) {
		return applicationContext.getParent() == null;
	}

}
