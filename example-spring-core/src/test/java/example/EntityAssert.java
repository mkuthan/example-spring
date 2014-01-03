package example;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.AbstractAssert;

import example.domain.shared.ddd.Entity;

public class EntityAssert extends AbstractAssert<EntityAssert, Entity> {

	public EntityAssert(Entity actual) {
		super(actual, EntityAssert.class);
	}

	public EntityAssert isEqualTo(Entity entity) {
		assertThat(actual.getEntityId()).isEqualTo(entity.getEntityId());

		return this;
	}

	public EntityAssert isManaged() {
		assertThat(actual.isManaged());

		return this;
	}

}
