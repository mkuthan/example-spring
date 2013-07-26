package example.security.domain;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.QueryHints;

import example.shared.ddd.DomainRepository;

public interface UserRepository extends DomainRepository<User> {

	@QueryHints(@QueryHint(name = org.hibernate.ejb.QueryHints.HINT_CACHEABLE, value = "true"))
	User findByIdentifier(UserIdentifier identifier);

}
