package example.ddd;

public interface EventPublisher {

	<T> void publish(Event<T> event);

}