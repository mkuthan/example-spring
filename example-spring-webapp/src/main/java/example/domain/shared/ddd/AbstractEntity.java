package example.domain.shared.ddd;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity implements Entity {

	@Id
	@GeneratedValue
	private Long entityId;

	@Override
	public Long getEntityId() {
		return this.entityId;
	}

	@Override
	public boolean isManaged() {
		// Access entityId property directly to avoid entity loading from
		// database
		return this.entityId != null;
	}

}