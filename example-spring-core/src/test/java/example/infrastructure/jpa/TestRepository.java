package example.infrastructure.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestEntity, Long> {

	List<TestEntity> findByName(String name);

}
