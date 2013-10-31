package example;

import org.fest.assertions.GenericAssert;

import example.domain.shared.ddd.Entity;

public class EntityAssert extends GenericAssert<EntityAssert, Entity> {

	public static EntityAssert assertThat(Entity actual) {
		return new EntityAssert(actual);
	}

	public EntityAssert(Entity actual) {
		super(EntityAssert.class, actual);
	}

	public EntityAssert hasSameIdentity(Entity entity) {
		org.fest.assertions.Assertions.assertThat(actual.getEntityId()).isEqualTo(entity.getEntityId());

		return this;
	}

	public EntityAssert isManaged() {
		org.fest.assertions.Assertions.assertThat(actual.isManaged());

		return this;
	}

}
