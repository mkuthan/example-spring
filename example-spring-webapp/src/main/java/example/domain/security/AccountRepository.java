package example.domain.security;

import example.domain.shared.ddd.DomainRepository;

public interface AccountRepository extends DomainRepository<Account> {

	Account findByUsername(String username);

}
