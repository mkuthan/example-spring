package example.infrastructure.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAttribute;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.stereotype.Component;

import example.security.domain.User;
import example.security.domain.User.Builder;
import example.security.domain.UserIdentifier;
import example.security.domain.UserRepository;

@Component("userService")
public class DefaultUserDetailsService implements UserDetailsService,
		AuthenticationUserDetailsService<OpenIDAuthenticationToken> {

	private static final List<GrantedAuthority> DEFAULT_AUTHORITIES = AuthorityUtils.createAuthorityList("ROLE_USER");

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetails loadUserDetails(OpenIDAuthenticationToken token) throws UsernameNotFoundException {
		String identity = token.getIdentityUrl();
		UserIdentifier identifier = new UserIdentifier(identity);

		User user = userRepository.findByIdentifier(identifier);
		if (user == null) {
			user = registerUser(identifier, token);
		}

		return new DefaultUserDetails(user, DEFAULT_AUTHORITIES);
	}

	private User registerUser(UserIdentifier identifier, OpenIDAuthenticationToken token) {
		Builder builder = new Builder();
		builder.withIdentifier(identifier);

		for (OpenIDAttribute attribute : token.getAttributes()) {
			if (attribute.getName().equals("email")) {
				builder.withEmail(attribute.getValues().get(0));
			}

			if (attribute.getName().equals("firstname")) {
				builder.withFirstname(attribute.getValues().get(0));
			}

			if (attribute.getName().equals("lastname")) {
				builder.withLastname(attribute.getValues().get(0));
			}

			if (attribute.getName().equals("fullname")) {
				builder.withFullname(attribute.getValues().get(0));
			}
		}
		return userRepository.saveAndFlush(builder.build());
	}

}
