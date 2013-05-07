package example.domain.shared.audit;

import static com.google.common.base.Preconditions.checkNotNull;

import org.joda.time.DateTime;

import example.ddd.AbstractValueObject;

public class Audit extends AbstractValueObject {

	public static final Audit DEFAULT = new Audit();

	private DateTime creationDate;

	private DateTime modificationDate;

	private String creator;

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
