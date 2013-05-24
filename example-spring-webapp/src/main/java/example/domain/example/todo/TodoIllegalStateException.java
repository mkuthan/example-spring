package example.domain.example.todo;

import static com.google.common.base.Preconditions.checkNotNull;

public class TodoIllegalStateException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Todo todo;

	private String operation;

	public TodoIllegalStateException(Todo todo, String operation) {
		this.todo = checkNotNull(todo);
		this.operation = checkNotNull(operation);
	}

	@Override
	public String getMessage() {
		return "Cannot " + operation + ", todo status is: " + todo.getStatus() + ".";
	}

}