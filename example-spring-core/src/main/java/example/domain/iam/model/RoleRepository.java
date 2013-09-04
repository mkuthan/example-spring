package example.domain.iam.model;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.QueryHints;

import example.domain.shared.ddd.DomainRepository;

public interface RoleRepository extends DomainRepository<Role> {

	@QueryHints(@QueryHint(name = org.hibernate.ejb.QueryHints.HINT_CACHEABLE, value = "true"))
	Role findByIdentifier(RoleIdentifier identifier);

}
