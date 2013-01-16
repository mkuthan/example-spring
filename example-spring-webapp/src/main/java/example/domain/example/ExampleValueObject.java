package example.domain.example;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.persistence.Column;

import com.google.common.base.Objects;

import example.domain.shared.ddd.DomainValueObject;

@DomainValueObject
public class ExampleValueObject {

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

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		ExampleValueObject other = (ExampleValueObject) obj;
		return Objects.equal(fieldA, other.fieldA) && Objects.equal(fieldB, other.fieldB);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(fieldA, fieldB);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).addValue(fieldA).addValue(fieldB).toString();
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
