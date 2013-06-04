package example.bootstrap.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import example.bootstrap.BootstrapEvent;
import example.bootstrap.BootstrapOrder;
import example.domain.shared.security.Account;
import example.domain.shared.security.AccountRepository;

@Component
public class AccountBootstrapListener implements ApplicationListener<BootstrapEvent>, Ordered {

	public static final String USER = "user";
	public static final String ADMIN = "admin";

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public void onApplicationEvent(BootstrapEvent event) {
		if (!accountsExist()) {
			Account user = new Account(USER, "Regular User");
			Account admin = new Account(ADMIN, "Administrator");
			accountRepository.save(Lists.newArrayList(user, admin));
			accountRepository.flush();
		}
	}

	@Override
	public int getOrder() {
		return BootstrapOrder.ACCOUNT;
	}

	public boolean accountsExist() {
		return accountRepository.count() > 0;
	}

}
