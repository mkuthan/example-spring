package example.domain.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import example.bootstrap.BootstrapEvent;
import example.bootstrap.BootstrapOrder;
import example.domain.security.Account;
import example.domain.security.AccountBootstrapListener;
import example.domain.security.AccountRepository;

@Component
public class BlogBootstrapListener implements ApplicationListener<BootstrapEvent>, Ordered {

	@Autowired
	private BlogRepository blogRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Override
	@Transactional
	public void onApplicationEvent(BootstrapEvent event) {
		if (!blogsExist()) {
			Account marcinAccount = accountRepository.findByUsername(AccountBootstrapListener.MARCIN);
			Account magdaAccount = accountRepository.findByUsername(AccountBootstrapListener.MAGDA);

			Blog marcinBlog = new BlogBuilder().withName("Software Craftmanship")
					.withBlogger(new BloggerBuilder().withAccount(marcinAccount).withFirstName("Marcin").build())
					.build();
			Blog magdaBlog = new BlogBuilder().withName("Cooking Recipies")
					.withBlogger(new BloggerBuilder().withAccount(magdaAccount).withFirstName("Magda").build()).build();
			blogRepository.save(Lists.newArrayList(marcinBlog, magdaBlog));
		}
	}

	@Override
	public int getOrder() {
		return BootstrapOrder.BLOG;
	}
	
	private boolean blogsExist() {
		return blogRepository.count() > 0;
	}

}
