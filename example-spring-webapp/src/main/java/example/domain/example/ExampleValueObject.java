package example.domain.example;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.persistence.Column;

import example.ddd.AbstractValueObject;
import example.ddd.DomainValueObject;

@DomainValueObject
public class ExampleValueObject extends AbstractValueObject {

	public static final int FIELD_MAX_LENGTH = 100;

	@Column(length = FIELD_MAX_LENGTH)
	private String fieldA;

	@Column(length = FIELD_MAX_LENGTH)
	private String fieldB;

	@Column(length = FIELD_MAX_LENGTH)
	private String fieldC;

	protected ExampleValueObject() {
	}

	public ExampleValueObject(Builder builder) {
		this.fieldA = checkNotNull(builder.fieldA);
		this.fieldB = checkNotNull(builder.fieldB);
		this.fieldC = builder.fieldC;
	}

	public String getFieldA() {
		return fieldA;
	}

	public String getFieldB() {
		return fieldB;
	}

	public String getFieldC() {
		return fieldC;
	}

	public static class Builder {

		private String fieldA;

		private String fieldB;

		private String fieldC;

		public Builder withFieldA(String fieldA) {
			this.fieldA = fieldA;
			return this;
		}

		public Builder withFieldB(String fieldB) {
			this.fieldB = fieldB;
			return this;
		}

		public Builder withFieldC(String fieldC) {
			this.fieldC = fieldC;
			return this;
		}

		public ExampleValueObject build() {
			return new ExampleValueObject(this);
		}

	}

}
