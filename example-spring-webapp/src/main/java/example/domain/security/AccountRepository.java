package example.domain.security;

import example.ddd.DomainRepository;

public interface AccountRepository extends DomainRepository<Account> {

	Account findByUsername(String username);

}
