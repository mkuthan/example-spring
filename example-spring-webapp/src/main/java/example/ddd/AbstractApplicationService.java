package example.ddd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class AbstractApplicationService {

	@Autowired
	private EventPublisher eventPublisher;

	protected <T> void publish(Event<T> event) {
		eventPublisher.publish(event);
	}

}
