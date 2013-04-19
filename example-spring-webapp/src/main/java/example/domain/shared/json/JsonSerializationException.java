package example.domain.shared.json;

public class JsonSerializationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public JsonSerializationException() {
		super();
	}

	public JsonSerializationException(String message, Throwable cause) {
		super(message, cause);
	}

	public JsonSerializationException(String message) {
		super(message);
	}

	public JsonSerializationException(Throwable cause) {
		super(cause);
	}

}
