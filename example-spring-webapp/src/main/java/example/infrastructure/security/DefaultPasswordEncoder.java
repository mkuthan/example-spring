package example.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Component;

import example.shared.security.PasswordEncoder;

@Component
public class DefaultPasswordEncoder implements PasswordEncoder {

	@Autowired
	private ShaPasswordEncoder encoder;

	@Autowired
	private String passwordSalt;

	@Override
	public String encode(String rawPassword) {
		return encoder.encodePassword(rawPassword, passwordSalt);
	}

}
