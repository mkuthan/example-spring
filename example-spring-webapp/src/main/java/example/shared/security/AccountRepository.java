package example.shared.security;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.QueryHints;

import example.shared.ddd.DomainRepository;

public interface AccountRepository extends DomainRepository<Account> {

	@QueryHints(@QueryHint(name = org.hibernate.ejb.QueryHints.HINT_CACHEABLE, value = "true"))
	Account findByUsername(String username);

}
