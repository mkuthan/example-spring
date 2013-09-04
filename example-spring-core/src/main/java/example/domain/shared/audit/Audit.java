package example.domain.shared.audit;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.joda.time.DateTime;

import example.domain.shared.ddd.AbstractValueObject;

@Embeddable
@Access(AccessType.FIELD)
public class Audit extends AbstractValueObject {

	public static final Audit NULL = new Audit();

	@Column(name = "audit_creation_date", nullable = false, updatable = false)
	private DateTime creationDate;

	@Column(name = "audit_creator", nullable = false, updatable = false)
	private String creator;

	@Column(name = "audit_modification_date", nullable = false)
	private DateTime modificationDate;

	@Column(name = "audit_modifier", nullable = false)
	private String modifier;

	protected Audit() {
	}

	public Audit(DateTime creationDate, String creator, DateTime modificationDate, String modifier) {
		this.creationDate = checkNotNull(creationDate);
		this.creator = checkNotNull(creator);
		this.modificationDate = checkNotNull(modificationDate);
		this.modifier = checkNotNull(modifier);
	}

	public Audit update(DateTime modificationDate, String modifier) {
		DateTime creationDate = (this.creationDate == null) ? modificationDate : this.creationDate;
		String creator = (this.creator == null) ? modifier : this.modifier;

		return new Audit(creationDate, creator, modificationDate, modifier);
	}

	public DateTime getCreationDate() {
		checkState(!isNull());
		return creationDate;
	}

	public String getCreator() {
		checkState(!isNull());
		return creator;
	}

	public DateTime getModificationDate() {
		checkState(!isNull());
		return modificationDate;
	}

	public String getModifier() {
		checkState(!isNull());
		return modifier;
	}

	private boolean isNull() {
		return equals(NULL);
	}

}
