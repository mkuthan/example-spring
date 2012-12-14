package example.domain.blog;

import example.domain.shared.ddd.DomainRepository;

public interface BlogRepository extends DomainRepository<Blog> {

	Blog findByName(String name);

}
