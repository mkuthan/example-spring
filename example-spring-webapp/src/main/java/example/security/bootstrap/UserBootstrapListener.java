package example.security.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import example.security.domain.User;
import example.security.domain.UserRepository;
import example.shared.bootstrap.BootstrapEvent;
import example.shared.bootstrap.BootstrapOrder;

@Component
public class UserBootstrapListener implements ApplicationListener<BootstrapEvent>, Ordered {

	public static final String USER = "user";
	public static final String ADMIN = "admin";

	@Autowired
	private UserRepository userRepository;

	@Override
	public void onApplicationEvent(BootstrapEvent event) {
		if (!accountsExist()) {
			User user = new User(USER, "Regular User");
			User admin = new User(ADMIN, "Administrator");
			userRepository.save(Lists.newArrayList(user, admin));
			userRepository.flush();
		}
	}

	@Override
	public int getOrder() {
		return BootstrapOrder.ACCOUNT;
	}

	public boolean accountsExist() {
		return userRepository.count() > 0;
	}

}
