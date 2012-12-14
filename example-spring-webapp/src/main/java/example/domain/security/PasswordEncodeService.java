package example.domain.security;

import example.domain.shared.ddd.DomainService;

@DomainService
public interface PasswordEncodeService {

	String encode(String rawPassword);

}
