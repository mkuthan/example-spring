package example.infrastructure.date;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import example.domain.shared.date.DateTimeProvider;

@Component
public class DefaultDateTimeProvider implements DateTimeProvider {

	@Override
	public DateTime currentDateTime() {
		return new DateTime();
	}

}
