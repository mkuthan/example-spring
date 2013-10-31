package example.domain.shared.ddd;

public interface EventListener<E extends Event<?>> {
	void listen(E event);
}
