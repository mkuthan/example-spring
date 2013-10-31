package example.domain.shared.ddd;

public abstract class AbstractApplicationService {

	protected void publish(Event<?> event) {
		EventPublisher.publish(event);
	}

}
