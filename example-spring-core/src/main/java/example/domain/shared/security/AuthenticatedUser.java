package example.domain.shared.security;

public interface AuthenticatedUser {

	String getUsername();

	String getEmail();

	String getFirstname();

	String getLastname();

	String getFullname();

}
