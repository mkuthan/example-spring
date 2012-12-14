package example.infrastructure.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Component;

import example.domain.security.PasswordEncodeService;

@Component
public class ShaPasswordEncodeService implements PasswordEncodeService {

	private ShaPasswordEncoder encoder;
	private String passwordSalt;

	@Autowired
	public ShaPasswordEncodeService(ShaPasswordEncoder encoder, String passwordSalt) {
		this.encoder = encoder;
		this.passwordSalt = passwordSalt;
	}

	@Override
	public String encode(String rawPassword) {
		return encoder.encodePassword(rawPassword, passwordSalt);
	}

}
