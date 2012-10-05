package example.domain.service;

import example.domain.shared.DomainService;

@DomainService
public interface PasswordEncodingService {

	String encode(String rawPassword);

}
