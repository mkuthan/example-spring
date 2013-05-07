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

	public static final String MARCIN = "marcin";
	public static final String MAGDA = "magda";

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public void onApplicationEvent(BootstrapEvent event) {
		if (!accountsExist()) {
			Account marcinAccount = new Account(MARCIN);
			Account magdaAccount = new Account(MAGDA);
			accountRepository.save(Lists.newArrayList(marcinAccount, magdaAccount));
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
