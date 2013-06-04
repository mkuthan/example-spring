package example.shared.json;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;

public class JsonHolder<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private T value;

	protected JsonHolder() {
	}

	public static <T> JsonHolder<T> of(T value) {
		JsonHolder<T> jsonHolder = new JsonHolder<T>();
		jsonHolder.value = checkNotNull(value);
		return jsonHolder;
	}

	public static <T> JsonHolder<T> absent() {
		return new JsonHolder<T>();
	}

	public T getValue() {
		return value;
	}

	public boolean hasValue() {
		return value != null;
	}

}
