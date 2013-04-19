package example.domain.example;

import java.util.List;

import example.ddd.DomainRepository;

public interface ExampleRepository extends DomainRepository<ExampleEntity> {

	List<ExampleEntity> findByName(String name);

	List<ExampleEntity> findByEmbeddedValueObject(ExampleValueObject exampleValueObject);

}
