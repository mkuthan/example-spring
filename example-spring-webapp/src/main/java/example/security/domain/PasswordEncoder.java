package example.security.domain;

import example.shared.ddd.DomainService;

@DomainService
public interface PasswordEncoder {

	String encode(String rawPassword);

}
