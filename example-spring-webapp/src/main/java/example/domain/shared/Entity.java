package example.domain.shared;

public interface Entity<T extends Entity<T>> {

	Long getEntityId();

	boolean sameIdentityAs(T entity);

	boolean isManaged();

}