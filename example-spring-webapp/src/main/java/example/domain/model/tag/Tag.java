package example.domain.model.tag;

import javax.persistence.Cacheable;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import example.domain.shared.AbstractAggregateEntity;
import example.domain.shared.DomainEntity;

@DomainEntity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Tag extends AbstractAggregateEntity<Tag> {

}
