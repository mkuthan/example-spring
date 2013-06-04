package example;

import example.shared.ddd.Entity;

public class Assertions {

	private Assertions() {
	}

	public static EntityAssert assertThat(Entity actual) {
		return new EntityAssert(actual);
	}

	public static EntitiesAssert assertThat(Iterable<? extends Entity> actual) {
		return new EntitiesAssert(actual);
	}

}
