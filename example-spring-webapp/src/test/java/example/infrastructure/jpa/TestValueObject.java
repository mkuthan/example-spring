package example.infrastructure.jpa;

import static com.google.common.base.Preconditions.*;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import example.shared.ddd.AbstractValueObject;

@Embeddable
@Access(AccessType.FIELD)
public class TestValueObject extends AbstractValueObject {

	@Column(nullable = false)
	private String fieldA;

	@Column(nullable = true)
	private String fieldB;

	protected TestValueObject() {
	}

	public TestValueObject(String fieldA) {
		this.fieldA = checkNotNull(fieldA);
	}

	public TestValueObject(String fieldA, String fieldB) {
		this(fieldA);
		this.fieldB = checkNotNull(fieldB);
	}

	public String getFieldA() {
		return fieldA;
	}

	public String getFieldB() {
		return fieldB;
	}
}
