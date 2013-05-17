package example.ddd;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;

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

	protected <T> void publish(Event<T> event) {
		eventPublisher.publish(event);
	}

}