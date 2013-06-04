package example.shared.audit;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.joda.time.DateTime;

import example.shared.ddd.AbstractValueObject;

@Embeddable
@Access(AccessType.FIELD)
public class Audit extends AbstractValueObject {

	public static final Audit DEFAULT = new Audit();

	@Column(name="audit_creation_date", nullable=false)
	private DateTime creationDate;

	@Column(name="audit_modification_date", nullable=false)
	private DateTime modificationDate;

	@Column(name="audit_creator", nullable=false)
	private String creator;

	@Column(name="audit_modifier", nullable=false)
	private String modifier;

	protected Audit() {
	}

	public Audit(DateTime creationDate, DateTime modificationDate, String creator, String modifier) {
		this.creationDate = checkNotNull(creationDate);
		this.modificationDate = checkNotNull(modificationDate);
		this.creator = checkNotNull(creator);
		this.modifier = checkNotNull(modifier);
	}

	public Audit update(DateTime now, String modifier) {
		if (isNew()) {
			return new Audit(now, now, modifier, modifier);
		}

		return new Audit(this.creationDate, now, this.creator, modifier);
	}

	public DateTime getCreationDate() {
		return creationDate;
	}

	public DateTime getModificationDate() {
		return modificationDate;
	}

	public String getCreator() {
		return creator;
	}

	public String getModifier() {
		return modifier;
	}

	private boolean isNew() {
		return creationDate == null && creator == null;
	}

}
