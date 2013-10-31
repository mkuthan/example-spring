package example;

import org.fest.assertions.GenericAssert;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterables;

import example.domain.shared.ddd.Entity;
import example.domain.shared.ddd.EntityFunctions;

public class EntitiesAssert extends GenericAssert<EntitiesAssert, Iterable<? extends Entity>> {

	public static EntitiesAssert assertThat(Iterable<? extends Entity> actual) {
		return new EntitiesAssert(actual);
	}

	public EntitiesAssert(Iterable<? extends Entity> actual) {
		super(EntitiesAssert.class, actual);
	}

	public EntitiesAssert contains(Entity entity) {
		org.fest.assertions.Assertions.assertThat(Iterables.transform(actual, EntityFunctions.toEntityId())).contains(
				entity.getEntityId());

		return this;
	}

	public EntitiesAssert containsOnly(Iterable<? extends Entity> entities) {
		org.fest.assertions.Assertions.assertThat(Iterables.transform(actual, EntityFunctions.toEntityId()))
				.containsOnly(
						(Object[]) FluentIterable.from(entities).transform(EntityFunctions.toEntityId())
								.toArray(Long.class));

		return this;
	}

}
