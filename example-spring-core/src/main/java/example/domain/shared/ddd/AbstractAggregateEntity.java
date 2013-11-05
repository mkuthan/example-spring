package example.domain.shared.ddd;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;

@MappedSuperclass
public class AbstractAggregateEntity extends AbstractEntity implements AggregateEntity {

	@Version
	private Integer entityVersion;

	@Autowired
	@Transient
	private EventPublisher eventPublisher;

	@Override
	public Integer getEntityVersion() {
		return this.entityVersion;
	}

	@Override
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).appendSuper(super.toString());
		if (entityVersion != null) {
			sb.append("version", entityVersion);
		}
		return sb.toString();
	}

	protected void publish(Event<?> event) {
		eventPublisher.publish(event);
	}

}