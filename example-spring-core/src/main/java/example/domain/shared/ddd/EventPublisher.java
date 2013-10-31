package example.domain.shared.ddd;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.EventBus;

@Component
public class EventPublisher {

	private static EventBus eventBus;

	private static List<EventListener<? extends Event<?>>> listeners = new CopyOnWriteArrayList<EventListener<? extends Event<?>>>();

	@edu.umd.cs.findbugs.annotations.SuppressWarnings("ST_WRITE_TO_STATIC_FROM_INSTANCE_METHOD")
	@Autowired
	public EventPublisher(EventBus eventBus) {
		EventPublisher.eventBus = eventBus;
	}

	public static void addEventListener(EventListener<? extends Event<?>> listener) {
		listeners.add(checkNotNull(listener));
	}

	public static void removeEventListener(EventListener<? extends Event<?>> listener) {
		listeners.remove(checkNotNull(listener));
	}

	public static void publish(Event<?> event) {
		if (eventBus != null) {
			eventBus.post(event);
		}
		fireEventPublished(event);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void fireEventPublished(Event<?> event) {
		for (EventListener listener : listeners) {
			listener.listen(event);
		}
	}

}