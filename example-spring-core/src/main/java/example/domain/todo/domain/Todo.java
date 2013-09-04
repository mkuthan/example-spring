package example.domain.todo.domain;

import static com.google.common.base.Preconditions.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import example.domain.shared.ddd.AbstractAggregateEntity;

@Entity
public class Todo extends AbstractAggregateEntity {

	public final static Status DEFAULT_STATUS = Status.ACTIVE;

	@Column
	@Enumerated(EnumType.STRING)
	private Status status = DEFAULT_STATUS;

	@Column
	private String title;

	protected Todo() {
	}

	public Todo(String title) {
		this.title = checkNotNull(title);
	}

	public Status getStatus() {
		return status;
	}

	public String getTitle() {
		return title;
	}

	public boolean isActive() {
		return status.isActive();
	}

	public boolean isDone() {
		return status.isDone();
	}

	public void done() {
		status.done(this);
	}

	public void updateTitle(String title) {
		status.updateTitle(this, title);
	}

	private void doUpdateTitle(String title) {
		this.title = checkNotNull(title);
	}

	private void doDone() {
		this.status = Status.DONE;
	}

	public enum Status implements TodoState {

		ACTIVE(new ActiveTodoState()), DONE(new DoneTodoState());

		private TodoState state;

		private Status(TodoState state) {
			this.state = checkNotNull(state);
		}

		@Override
		public boolean isActive() {
			return state.isActive();
		}

		@Override
		public boolean isDone() {
			return state.isDone();
		}

		@Override
		public void updateTitle(Todo todo, String title) {
			state.updateTitle(todo, title);
		}

		@Override
		public void done(Todo todo) {
			state.done(todo);
		}

	}

	public static class TodoStateAdapter implements TodoState {

		@Override
		public boolean isActive() {
			return false;
		}

		@Override
		public boolean isDone() {
			return false;
		}

		@Override
		public void updateTitle(Todo todo, String title) {
			throw new TodoIllegalStateException(todo, "updateTitle");
		}

		@Override
		public void done(Todo todo) {
			throw new TodoIllegalStateException(todo, "done");
		}

	}

	public static class ActiveTodoState extends TodoStateAdapter {

		@Override
		public boolean isActive() {
			return true;
		}

		@Override
		public void updateTitle(Todo todo, String title) {
			todo.doUpdateTitle(title);
		}

		@Override
		public void done(Todo todo) {
			todo.doDone();
		}

	}

	public static class DoneTodoState extends TodoStateAdapter {

		@Override
		public boolean isDone() {
			return true;
		}
	}
}
