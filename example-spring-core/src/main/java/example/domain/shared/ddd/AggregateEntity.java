package example.domain.shared.ddd;

public interface AggregateEntity extends Entity {

	Integer getEntityVersion();

}