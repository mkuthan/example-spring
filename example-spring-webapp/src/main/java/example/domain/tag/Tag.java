package example.domain.tag;

import javax.persistence.Cacheable;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import example.domain.shared.ddd.AbstractAggregateEntity;
import example.domain.shared.ddd.DomainEntity;

@DomainEntity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Tag extends AbstractAggregateEntity {

}
