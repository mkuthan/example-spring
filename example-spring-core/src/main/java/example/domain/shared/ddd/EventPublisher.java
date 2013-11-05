package example.domain.shared.ddd;

public interface EventPublisher {

	void publish(Event<?> event);

}