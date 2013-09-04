package example.domain.shared.ddd;

import com.google.common.base.Function;

public final class EntityFunctions {

	private EntityFunctions() {
	}

	public static Function<Entity, Long> toEntityId() {
		return new Function<Entity, Long>() {
			@Override
			public Long apply(Entity input) {
				return input.getEntityId();
			}
		};
	}

}
