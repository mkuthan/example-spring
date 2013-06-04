package example.shared.security;

import example.shared.ddd.DomainService;

@DomainService
public interface PasswordEncoder {

	String encode(String rawPassword);

}
