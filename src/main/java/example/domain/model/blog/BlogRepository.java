package example.domain.model.blog;

import example.domain.shared.DomainRepository;

public interface BlogRepository extends DomainRepository<Blog> {

    Blog findByName(String name);

}
