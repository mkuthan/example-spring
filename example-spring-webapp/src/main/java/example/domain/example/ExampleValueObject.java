package example.domain.example;

import static com.google.common.base.Preconditions.*;

import javax.persistence.Column;

import example.domain.shared.ddd.AbstractValueObject;
import example.domain.shared.ddd.DomainValueObject;

@DomainValueObject
public class ExampleValueObject extends AbstractValueObject {

	public static final int FIELD_MAX_LENGTH = 100;

	@Column(length = FIELD_MAX_LENGTH)
	private String fieldA;

	@Column(length = FIELD_MAX_LENGTH)
	private String fieldB;

	protected ExampleValueObject() {
	}

	public ExampleValueObject(String fieldA, String fieldB) {
		this.fieldA = checkNotNull(fieldA);
		this.fieldB = checkNotNull(fieldB);
	}

	public String getFieldA() {
		return fieldA;
	}

	public String getFieldB() {
		return fieldB;
	}

	public static class Builder {

		private String fieldA;

		private String fieldB;

		public Builder withFieldA(String fieldA) {
			this.fieldA = fieldA;
			return this;
		}

		public Builder withFieldB(String fieldB) {
			this.fieldB = fieldB;
			return this;
		}

		public ExampleValueObject build() {
			return new ExampleValueObject(fieldA, fieldB);
		}

	}

}
