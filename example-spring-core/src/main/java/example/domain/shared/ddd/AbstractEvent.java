package example.domain.shared.ddd;

import static com.google.common.base.Preconditions.checkNotNull;

public class AbstractEvent<T> implements Event {

	private static final long serialVersionUID = 1L;

	private T payload;

	public AbstractEvent(T payload) {
		this.payload = checkNotNull(payload);
	}

	public T getPayload() {
		return payload;
	}

}
