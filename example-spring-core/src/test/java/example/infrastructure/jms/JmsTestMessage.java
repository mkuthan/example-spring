package example.infrastructure.jms;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class JmsTestMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID uuid = UUID.randomUUID();

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		JmsTestMessage other = (JmsTestMessage) obj;
		return Objects.equals(uuid, other.uuid);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(uuid);
	}

	@Override
	public String toString() {
		return uuid.toString();
	}
}