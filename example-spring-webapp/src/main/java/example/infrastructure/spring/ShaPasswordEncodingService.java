package example.infrastructure.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Component;

import example.domain.service.PasswordEncodingService;

@Component
public class ShaPasswordEncodingService implements PasswordEncodingService {

	private ShaPasswordEncoder encoder;
	private String passwordSalt;

	@Autowired
	public ShaPasswordEncodingService(ShaPasswordEncoder encoder, String passwordSalt) {
		this.encoder = encoder;
		this.passwordSalt = passwordSalt;
	}

	@Override
	public String encode(String rawPassword) {
		return encoder.encodePassword(rawPassword, passwordSalt);
	}

}
