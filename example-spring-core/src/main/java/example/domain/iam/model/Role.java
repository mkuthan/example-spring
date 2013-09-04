package example.domain.iam.model;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.persistence.AttributeOverride;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import example.domain.shared.ddd.AbstractAggregateEntity;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Role extends AbstractAggregateEntity {

	@Embedded
	@AttributeOverride(name = RoleIdentifier.IDENTIFIER_PROPERTY, column = @Column(nullable = false, unique = true))
	private RoleIdentifier identifier;

	@Column(nullable = false)
	private String name;

	protected Role() {
	}

	public Role(RoleIdentifier identifier, String name) {
		this.identifier = checkNotNull(identifier);
		this.name = checkNotNull(name);
	}

	public RoleIdentifier getIdentifier() {
		return identifier;
	}

	public String getName() {
		return name;
	}

}
