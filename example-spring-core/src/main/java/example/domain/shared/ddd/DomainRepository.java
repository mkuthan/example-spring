package example.domain.shared.ddd;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DomainRepository<T extends AggregateEntity> extends JpaRepository<T, Long> {
}
