package example.domain.shared.ddd;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractApplicationService {

	@Autowired
	private EventPublisher eventPublisher;

	protected void publish(Event<?> event) {
		eventPublisher.publish(event);
	}

}
