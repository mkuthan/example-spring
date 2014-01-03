package example.infrastructure.security;

import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class OpenIdUserDetailsService implements AuthenticationUserDetailsService<OpenIDAuthenticationToken> {

	//@Autowired
	//private UserRepository userRepository;

	@Override
	public UserDetails loadUserDetails(OpenIDAuthenticationToken token) throws UsernameNotFoundException {
		return null;
//		String identity = token.getIdentityUrl();
//		UserIdentifier identifier = new UserIdentifier(identity);
//
//		User user = userRepository.findByIdentifier(identifier);
//		if (user != null) {
//			// TODO: update account
//		} else {
//			// TODO: add user
//		}
//
//		Builder builder = new Builder().withAuthenticationToken(token);
//		for (Group group : user.effectiveGroups()) {
//			builder.addGrantedAuthority(new SimpleGrantedAuthority(group.getName()));
//		}
//
//		return builder.build();
	}

}
