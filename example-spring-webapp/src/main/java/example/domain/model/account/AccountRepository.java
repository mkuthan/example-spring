package example.domain.model.account;

import example.domain.shared.DomainRepository;

public interface AccountRepository extends DomainRepository<Account> {

	Account findByUsername(String username);

}
