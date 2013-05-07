package example.domain.shared.security;

import example.ddd.DomainService;

@DomainService
public interface PasswordEncoder {

	String encode(String rawPassword);

}
