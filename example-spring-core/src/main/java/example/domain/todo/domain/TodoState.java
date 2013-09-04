package example.domain.todo.domain;

public interface TodoState {

	boolean isActive();

	boolean isDone();

	void updateTitle(Todo todo, String title);

	void done(Todo todo);
	
}
