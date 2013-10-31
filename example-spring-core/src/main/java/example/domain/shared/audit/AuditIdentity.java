package example.domain.shared.audit;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

import example.domain.shared.ddd.AbstractValueObject;

@Embeddable
@Access(AccessType.FIELD)
public class AuditIdentity extends AbstractValueObject {

	public static final AuditIdentity NOT_AVAILABLE = new AuditIdentity("N/A", "N/A");

	public static final String IDENTITY_PROPERTY_NAME = "identity";

	public static final String DETAILS_PROPERTY_NAME = "details";

	private String identity;

	private String details;

	protected AuditIdentity() {
	}

	public AuditIdentity(String identity, String details) {
		this.identity = checkNotNull(identity);
		this.details = checkNotNull(details);
	}

	public String getIdentity() {
		return identity;
	}

	public String getDetails() {
		return details;
	}

}
