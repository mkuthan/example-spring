package example.domain.shared.json;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;

public class JsonHolder<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private T value;

	private String type;

	public JsonHolder() {
	}

	public JsonHolder(T value) {
		setValue(value);
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = checkNotNull(value);
	}

	public boolean hasValue() {
		return value != null;
	}

	/**
	 * DO NOT USE, only for internal Hibernate custom type implementation.
	 * 
	 * @return Value type calculated based on value or null if value is null
	 */
	public String getValueType() {
		return hasValue() ? value.getClass().getName() : null;
	}

	/**
	 * DO NOT USE, only for internal Hibernate custom type implementation.
	 * 
	 * @return Value type from database field
	 */
	public String getType() {
		return type;
	}

	/**
	 * DO NOT USE, only for internal Hibernate custom type implementation.
	 * 
	 * @param type
	 *            Value type from database field
	 */
	public void setType(String type) {
		this.type = checkNotNull(type);
	}

}
