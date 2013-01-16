package example.bootstrap.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import example.bootstrap.BootstrapEvent;
import example.domain.blog.Blog;
import example.domain.blog.BlogBuilder;
import example.domain.blog.BlogRepository;
import example.domain.blog.BloggerBuilder;
import example.domain.security.Account;
import example.domain.security.AccountRepository;

public class BlogBootstrapListener implements ApplicationListener<BootstrapEvent> {

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

	private boolean blogsExist() {
		return blogRepository.count() > 0;
	}

}
