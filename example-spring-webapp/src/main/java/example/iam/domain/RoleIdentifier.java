package example.iam.domain;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import example.shared.ddd.AbstractValueObject;

@Embeddable
@Access(AccessType.FIELD)
public class RoleIdentifier extends AbstractValueObject {

	@Column(nullable = false, unique = true)
	private String identifier;

	protected RoleIdentifier() {
	}

	public RoleIdentifier(String identifier) {
		this.identifier = checkNotNull(identifier);
	}

	public String getIdentifier() {
		return identifier;
	}

}
