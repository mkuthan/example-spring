package example.domain.iam.model;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

import example.domain.shared.ddd.AbstractValueObject;

@Embeddable
@Access(AccessType.FIELD)
public class UserIdentifier extends AbstractValueObject {

	public static final String IDENTIFIER_PROPERTY = "identifier";

	private String identifier;

	protected UserIdentifier() {
	}

	public UserIdentifier(String identifier) {
		this.identifier = checkNotNull(identifier);
	}

	public String getIdentifier() {
		return identifier;
	}

}
