package example.domain.shared;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity<T extends AbstractEntity<T>> implements Entity<T> {

	@Id
	@GeneratedValue
	private Long entityId;

	@Override
	public Long getEntityId() {
		return this.entityId;
	}

	@Override
	public boolean sameIdentityAs(T entity) {
		// Access entityId property directly to avoid entity loading from
		// database
		return isManaged() ? this.entityId.equals(entity.entityId) : false;
	};

	@Override
	public boolean isManaged() {
		// Access entityId property directly to avoid entity loading from
		// database
		return this.entityId != null;
	}

}