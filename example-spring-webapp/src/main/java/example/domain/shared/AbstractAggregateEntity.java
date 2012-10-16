package example.domain.shared;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public class AbstractAggregateEntity<T extends AbstractAggregateEntity<T>>
		extends AbstractEntity<T> implements AggregateEntity<T> {

	@Version
	private Integer entityVersion;

	@Override
	public Integer getEntityVersion() {
		return this.entityVersion;
	}

}