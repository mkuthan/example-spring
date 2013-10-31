package example.domain.iam.model;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;

public interface RoleRepository extends JpaRepository<Role, Long> {

	@QueryHints(@QueryHint(name = org.hibernate.ejb.QueryHints.HINT_CACHEABLE, value = "true"))
	Role findByIdentifier(RoleIdentifier identifier);

}
