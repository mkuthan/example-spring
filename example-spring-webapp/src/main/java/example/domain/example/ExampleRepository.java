package example.domain.example;

import java.util.List;

import example.domain.shared.ddd.DomainRepository;

public interface ExampleRepository extends DomainRepository<ExampleEntity> {
	
	List<ExampleEntity> findByName(String name);
	
}
