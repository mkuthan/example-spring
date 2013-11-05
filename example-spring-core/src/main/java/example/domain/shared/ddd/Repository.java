package example.domain.shared.ddd;

import java.io.Serializable;
import java.util.List;

public interface Repository<E extends AbstractAggregateEntity, K extends Serializable> {

	E load(K entityId);
	
	List<E> loadAll();

	long count();

	E save(E entity);

	Iterable<E> saveAll(Iterable<E> entities);

	void delete(E entity);

}
