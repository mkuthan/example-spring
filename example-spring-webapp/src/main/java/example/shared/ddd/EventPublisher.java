package example.shared.ddd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.EventBus;

@Component
public class EventPublisher {

	private static EventBus eventBus;

	@edu.umd.cs.findbugs.annotations.SuppressWarnings("ST_WRITE_TO_STATIC_FROM_INSTANCE_METHOD")
	@Autowired
	public void setEventBus(EventBus eventBus) {
		EventPublisher.eventBus = eventBus;
	}

	public static void publish(Event<?> event) {
		eventBus.post(event);
	}

}