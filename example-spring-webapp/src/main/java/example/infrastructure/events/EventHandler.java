package example.infrastructure.events;

public interface EventHandler {

	boolean canHandle(Object event);

	void handle(Object event);

}
