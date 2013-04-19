package example.assertions;

import static org.fest.assertions.Assertions.assertThat;

import org.fest.assertions.GenericAssert;

import example.ddd.Entity;

public class EntityAssert extends GenericAssert<EntityAssert, Entity> {

	public EntityAssert(Entity actual) {
		super(EntityAssert.class, actual);
	}

	public EntityAssert hasSameIdentity(Entity entity) {
		assertThat(actual.getEntityId()).isEqualTo(entity.getEntityId());

		return this;
	}

}
