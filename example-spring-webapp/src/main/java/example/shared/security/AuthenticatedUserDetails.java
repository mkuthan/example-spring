package example.shared.security;

public interface AuthenticatedUserDetails {

	String getUsername();

	String getEmail();

	String getFirstname();

	String getLastname();

	String getFullname();

}
