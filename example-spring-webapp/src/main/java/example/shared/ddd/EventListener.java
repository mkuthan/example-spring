package example.shared.ddd;

public interface EventListener<E extends Event> {
	void listen(E event);
}
