package example.infrastructure.jpa;

import java.util.List;

import example.shared.ddd.DomainRepository;

public interface TestRepository extends DomainRepository<TestEntity> {

	List<TestEntity> findByName(String name);

}
