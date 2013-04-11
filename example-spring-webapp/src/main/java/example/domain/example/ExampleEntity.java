package example.domain.example;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.persistence.Column;
import javax.persistence.Embedded;

import com.google.common.base.Objects;

import example.domain.shared.ddd.AbstractAggregateEntity;
import example.domain.shared.ddd.DomainEntity;

@DomainEntity
public class ExampleEntity extends AbstractAggregateEntity {

	public static final int NAME_MAX_LENGTH = 100;

	@Column(nullable = true, unique = true, length = NAME_MAX_LENGTH)
	private String name;

	@Embedded
	private ExampleValueObject value;

	protected ExampleEntity() {
	}

	public ExampleEntity(String name) {
		this.name = checkNotNull(name);
	}

	public String getName() {
		return name;
	}

	public ExampleValueObject getValue() {
		return value;
	}

	public void setValue(ExampleValueObject value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).addValue(name).addValue(value).toString();
	}

	public static class Builder {

		private String name;

		private ExampleValueObject value;

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withValue(ExampleValueObject value) {
			this.value = value;
			return this;
		}

		public ExampleEntity build() {
			ExampleEntity exampleEntity = new ExampleEntity(name);
			exampleEntity.setValue(value);

			return exampleEntity;
		}
	}

}
