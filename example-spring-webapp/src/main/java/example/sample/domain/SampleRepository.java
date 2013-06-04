package example.sample.domain;

import java.util.List;

import example.shared.ddd.DomainRepository;

public interface SampleRepository extends DomainRepository<SampleEntity> {

	List<SampleEntity> findByName(String name);

	List<SampleEntity> findByEmbeddedValueObject(SampleValueObject sampleValueObject);

}
