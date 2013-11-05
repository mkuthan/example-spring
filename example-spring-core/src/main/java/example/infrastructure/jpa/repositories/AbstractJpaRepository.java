package example.infrastructure.jpa.repositories;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.orm.ObjectRetrievalFailureException;

import example.domain.shared.ddd.AbstractAggregateEntity;
import example.domain.shared.ddd.Repository;

public class AbstractJpaRepository<E extends AbstractAggregateEntity, K extends Serializable> implements
		Repository<E, K> {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private AutowireCapableBeanFactory beanFactory;

	private Class<E> clazz;

	@SuppressWarnings("unchecked")
	public AbstractJpaRepository() {
		this.clazz = ((Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
	}

	@Override
	public E load(K entityId) {
		checkNotNull(entityId);

		E loadedEntity = entityManager.find(clazz, entityId, LockModeType.OPTIMISTIC);

		if (loadedEntity == null) {
			throw new ObjectRetrievalFailureException(clazz, entityId);

		}

		beanFactory.autowireBean(loadedEntity);

		return loadedEntity;
	}

	@Override
	public List<E> loadAll() {
		StringBuffer queryString = new StringBuffer("SELECT e from ") //
				.append(clazz.getSimpleName()) //
				.append(" e");

		TypedQuery<E> query = entityManager.createQuery(queryString.toString(), clazz);
		return query.getResultList();
	}

	@Override
	public long count() {
		StringBuffer queryString = new StringBuffer("SELECT count(e) from ") //
				.append(clazz.getSimpleName()) //
				.append(" e");

		TypedQuery<Long> query = entityManager.createQuery(queryString.toString(), Long.class);
		return query.getSingleResult();
	}

	@Override
	public E save(E entity) {
		saveOne(entity);
		entityManager.flush();

		return entity;
	}

	@Override
	public Iterable<E> saveAll(Iterable<E> entities) {
		for (E entity : entities) {
			saveOne(entity);
		}
		entityManager.flush();

		return entities;
	}

	@Override
	public void delete(E entity) {
		checkNotNull(entity);

		entityManager.remove(entity);
	}

	private E saveOne(E entity) {
		checkNotNull(entity);

		if (!entity.isManaged()) {
			entityManager.persist(entity);
		} else {
			entityManager.lock(entity, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		}

		return entity;
	}
}
