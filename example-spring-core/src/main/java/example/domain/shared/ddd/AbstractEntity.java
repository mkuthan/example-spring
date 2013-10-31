package example.domain.shared.ddd;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
		// Access entityId property directly to avoid entity loading from database
		return this.entityId != null;
	}

	@Override
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
		// Access entityId property directly to avoid entity loading from database
		if (entityId != null) {
			sb.append("id", entityId);
		}
		return sb.toString();
	}

}