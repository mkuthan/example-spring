package example.infrastructure.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.EventBus;

import example.domain.shared.ddd.Event;
import example.domain.shared.ddd.EventPublisher;

@Component
public class EventBusEventPublisher implements EventPublisher {

	@Autowired
	private EventBus eventBus;

	@Override
	public void publish(Event<?> event) {
		eventBus.post(event);
	}

}