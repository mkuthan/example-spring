package example;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.AbstractAssert;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterables;

import example.domain.shared.ddd.Entity;
import example.domain.shared.ddd.EntityFunctions;

public class EntitiesAssert extends AbstractAssert<EntitiesAssert, Iterable<? extends Entity>> {

	public EntitiesAssert(Iterable<? extends Entity> actual) {
		super(actual, EntitiesAssert.class);
	}

	public EntitiesAssert contains(Entity entity) {
		assertThat(Iterables.transform(actual, EntityFunctions.toEntityId())).contains(entity.getEntityId());

		return this;
	}

	public EntitiesAssert containsOnly(Iterable<? extends Entity> entities) {
		assertThat(Iterables.transform(actual, EntityFunctions.toEntityId())).containsOnly(
				(Long[]) FluentIterable.from(entities).transform(EntityFunctions.toEntityId()).toArray(Long.class));

		return this;
	}

}
