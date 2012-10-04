package example.domain.shared;

public interface AggregateEntity<T extends AggregateEntity<T>> extends Entity<T> {

    Integer getEntityVersion();

}