package example.shared.date;

import org.joda.time.DateTime;

public interface DateTimeProvider {
	DateTime now();
}
