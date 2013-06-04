package example.infrastructure.date;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import example.shared.date.DateTimeProvider;

@Component
public class DefaultDateTimeProvider implements DateTimeProvider {

	@Override
	public DateTime now() {
		return new DateTime();
	}

}
