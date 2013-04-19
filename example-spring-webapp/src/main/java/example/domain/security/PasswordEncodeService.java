package example.domain.security;

import example.ddd.DomainService;

@DomainService
public interface PasswordEncodeService {

	String encode(String rawPassword);

}
