package example.domain.shared.ddd;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public class AbstractAggregateEntity extends AbstractEntity implements AggregateEntity {

	@Version
	private Integer entityVersion;

	@Override
	public Integer getEntityVersion() {
		return this.entityVersion;
	}

}