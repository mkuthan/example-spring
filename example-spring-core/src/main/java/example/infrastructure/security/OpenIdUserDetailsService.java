package example.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.stereotype.Component;

import example.domain.iam.model.RoleIdentifier;
import example.domain.iam.model.User;
import example.domain.iam.model.UserIdentifier;
import example.domain.iam.model.UserRepository;
import example.infrastructure.security.OpenIdUser.Builder;

@Component
public class OpenIdUserDetailsService implements AuthenticationUserDetailsService<OpenIDAuthenticationToken> {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserDetails(OpenIDAuthenticationToken token) throws UsernameNotFoundException {
		String identity = token.getIdentityUrl();
		UserIdentifier identifier = new UserIdentifier(identity);

		User user = userRepository.findByIdentifier(identifier);
		if (user != null) {
			// TODO: update account
		} else {
			// TODO: add user
		}

		Builder builder = new Builder().withAuthenticationToken(token);
		if (user != null) {
			for (RoleIdentifier role : user.getRoles()) {
				builder.addGrantedAuthority(new SimpleGrantedAuthority(role.getIdentifier()));
			}
		}

		return builder.build();
	}

}
