package example.domain.shared.audit;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import org.joda.time.DateTime;

import example.domain.shared.ddd.AbstractValueObject;

@Embeddable
@Access(AccessType.FIELD)
public class Audit extends AbstractValueObject {

	public static final Audit NULL = new Audit();

	@Column(name = "audit_creation_date", nullable = false, updatable = false)
	private DateTime creationDate;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = AuditIdentity.IDENTITY_PROPERTY_NAME, column = @Column(name = "audit_creator_identity", nullable = false, updatable = false)),
			@AttributeOverride(name = AuditIdentity.DETAILS_PROPERTY_NAME, column = @Column(name = "audit_creator_details", nullable = false, updatable = false)) })
	private AuditIdentity creator;

	@Column(name = "audit_modification_date", nullable = false)
	private DateTime modificationDate;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = AuditIdentity.IDENTITY_PROPERTY_NAME, column = @Column(name = "audit_modifier_identity", nullable = false)),
			@AttributeOverride(name = AuditIdentity.DETAILS_PROPERTY_NAME, column = @Column(name = "audit_modifier_details", nullable = false)) })
	private AuditIdentity modifier;

	protected Audit() {
	}

	protected Audit(DateTime creationDate, AuditIdentity creator, DateTime modificationDate, AuditIdentity modifier) {
		this.creationDate = checkNotNull(creationDate);
		this.creator = checkNotNull(creator);
		this.modificationDate = checkNotNull(modificationDate);
		this.modifier = checkNotNull(modifier);
	}

	public Audit update(DateTime modificationDate, AuditIdentity modifier) {
		DateTime creationDate = (this.creationDate == null) ? modificationDate : this.creationDate;
		AuditIdentity creator = (this.creator == null) ? modifier : this.modifier;

		return new Audit(creationDate, creator, modificationDate, modifier);
	}

	public DateTime getCreationDate() {
		checkState(!isNull());
		return creationDate;
	}

	public AuditIdentity getCreator() {
		checkState(!isNull());
		return creator;
	}

	public DateTime getModificationDate() {
		checkState(!isNull());
		return modificationDate;
	}

	public AuditIdentity getModifier() {
		checkState(!isNull());
		return modifier;
	}

	private boolean isNull() {
		return equals(NULL);
	}

}
