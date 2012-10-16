package example.infrastructure.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import example.domain.model.account.Account;
import example.domain.model.account.AccountRepository;
import example.domain.model.blog.Blog;
import example.domain.model.blog.BlogBuilder;
import example.domain.model.blog.BlogRepository;
import example.domain.model.blog.BloggerBuilder;

@Component
public class ApplicationBootstrap implements
		ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private BlogRepository blogRepository;

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (isRootApplicationContext(event)) {
			bootstrap();
		}
	}

	private void bootstrap() {
		Account marcinAccount = new Account("marcin");
		Account magdaAccount = new Account("magda");
		accountRepository.save(Lists.newArrayList(marcinAccount, magdaAccount));

		Blog marcinBlog = new BlogBuilder()
				.withName("Software Craftmanship")
				.withBlogger(
						new BloggerBuilder().withAccount(marcinAccount)
								.withFirstName("Marcin").build()).build();
		Blog magdaBlog = new BlogBuilder()
				.withName("Cooking Recipies")
				.withBlogger(
						new BloggerBuilder().withAccount(magdaAccount)
								.withFirstName("Magda").build()).build();
		blogRepository.save(Lists.newArrayList(marcinBlog, magdaBlog));
	}

	private boolean isRootApplicationContext(ContextRefreshedEvent event) {
		return event.getApplicationContext().getParent() == null;
	}
}