package example.iam.domain;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import example.shared.ddd.AbstractAggregateEntity;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Role extends AbstractAggregateEntity {

	@Embedded
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

	public void setIdentifier(RoleIdentifier identifier) {
		this.identifier = identifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
