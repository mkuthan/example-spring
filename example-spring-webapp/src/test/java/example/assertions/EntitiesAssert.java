package example.assertions;

import static org.fest.assertions.Assertions.assertThat;

import org.fest.assertions.GenericAssert;

import com.google.common.collect.Iterables;

import example.ddd.Entity;
import example.ddd.EntityFunctions;

public class EntitiesAssert extends GenericAssert<EntitiesAssert, Iterable<? extends Entity>> {

	public EntitiesAssert(Iterable<? extends Entity> actual) {
		super(EntitiesAssert.class, actual);
	}

	public EntitiesAssert containsWithSameIdentity(Entity entity) {
		assertThat(Iterables.transform(actual, EntityFunctions.toEntityId())).contains(entity.getEntityId());

		return this;
	}

}
