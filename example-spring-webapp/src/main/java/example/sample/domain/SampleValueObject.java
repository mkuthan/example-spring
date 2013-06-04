package example.sample.domain;

import static com.google.common.base.Preconditions.*;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import example.shared.ddd.AbstractValueObject;

@Embeddable
@Access(AccessType.FIELD)
public class SampleValueObject extends AbstractValueObject {

	public static final int FIELD_MAX_LENGTH = 100;

	@Column(length = FIELD_MAX_LENGTH)
	private String fieldA;

	@Column(length = FIELD_MAX_LENGTH)
	private String fieldB;

	@Column(length = FIELD_MAX_LENGTH)
	private String fieldC;

	protected SampleValueObject() {
	}

	public SampleValueObject(Builder builder) {
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

		public SampleValueObject build() {
			return new SampleValueObject(this);
		}

	}

}
