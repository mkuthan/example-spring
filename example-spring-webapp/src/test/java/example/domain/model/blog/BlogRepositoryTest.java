package example.domain.model.blog;

import static com.googlecode.catchexception.CatchException.*;
import static org.fest.assertions.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.base.Strings;

import example.TestGroups;
import example.domain.model.account.Account;
import example.domain.model.account.AccountRepository;

@Test(groups = { TestGroups.DB })
@ContextConfiguration(locations = "classpath:/META-INF/spring/testContext-infrastructure-jpa.xml")
@Profile("test")
public class BlogRepositoryTest extends AbstractTransactionalTestNGSpringContextTests {

	static final String EXISTING_BLOG_NAME = "EXISTING BLOG";
	static final String ANY_BLOG_NAME = "ANY BLOG";

	static final String EXISTING_BLOGGER_FIRST_NAME = "EXISTING BLOGGER FIRST NAME";
	static final String EXISTING_BLOGGER_LAST_NAME = "EXISTING BLOGGER LAST NAME";
	static final String ANY_BLOGGER_LAST_NAME = "ANY BLOGGER LAST NAME";
	static final String ANY_BLOGGER_FIRST_NAME = "ANY BLOGGER FIRST NAME";

	@Autowired
	BlogRepository blogRepository;

	BlogBuilder blogBuilder;
	BloggerBuilder bloggerBuilder;

	@Autowired
	AccountRepository accountRepository;

	@DataProvider
	static final Object[][] validBlogs() {
		String longBlogName = Strings.padEnd("Long Blog", Blog.NAME_MAX_LENGTH, '.');

		return new Object[][] { { ANY_BLOG_NAME }, { longBlogName } };
	}

	@DataProvider
	static final Object[][] invalidBlogs() {
		String longBlogName = Strings.padEnd("Long Blog", Blog.NAME_MAX_LENGTH + 1, '.');

		return new Object[][] { { EXISTING_BLOG_NAME }, { longBlogName } };
	}

	@DataProvider
	static final Object[][] validBloggers() {
		String longFirstName = Strings.padEnd("Blogger First Name", Blogger.FIRST_NAME_MAX_LENGTH, '.');
		String longLastName = Strings.padEnd("Blogger Last Name", Blogger.LAST_NAME_MAX_LENGTH, '.');

		return new Object[][] { { ANY_BLOGGER_FIRST_NAME, ANY_BLOGGER_LAST_NAME }, { longFirstName, longLastName } };
	}

	@DataProvider
	static final Object[][] invalidBloggers() {
		String longFirstName = Strings.padEnd("Blogger First Name", Blogger.FIRST_NAME_MAX_LENGTH + 1, '.');
		String longLastName = Strings.padEnd("Blogger Last Name", Blogger.LAST_NAME_MAX_LENGTH + 1, '.');

		return new Object[][] { { longFirstName, ANY_BLOGGER_LAST_NAME }, { ANY_BLOGGER_FIRST_NAME, longLastName } };
	}

	@BeforeMethod
	void setUp() {
		Account account = new Account("EXISTING ACCOUNT");
		this.accountRepository.save(account);

		this.bloggerBuilder = new BloggerBuilder().withAccount(account).withFirstName(EXISTING_BLOGGER_FIRST_NAME)
				.withLastName(EXISTING_BLOGGER_LAST_NAME);

		this.blogBuilder = new BlogBuilder().withName(EXISTING_BLOG_NAME).withBlogger(this.bloggerBuilder.build());

		this.blogRepository.saveAndFlush(this.blogBuilder.build());
	}

	public void shouldFindByNameExistingBlog() {
		// when
		Blog blog = this.blogRepository.findByName(EXISTING_BLOG_NAME);

		// then
		assertThat(blog).as("saved blog").isNotNull();
		assertThat(blog.getName()).isEqualTo(EXISTING_BLOG_NAME);
	}

	public void shouldDeleteExistingBlog() {
		// when
		Blog blog = this.blogRepository.findByName(EXISTING_BLOG_NAME);

		// when
		this.blogRepository.delete(blog);
		this.blogRepository.flush();

		// then
		boolean exists = this.blogRepository.exists(blog.getEntityId());
		assertThat(exists).isFalse();
	}

	@Test(dataProvider = "validBlogs")
	public void shouldSaveValidBlog(String blogName) {
		// given
		Blog blog = this.blogBuilder.withName(blogName).build();

		// when
		this.blogRepository.saveAndFlush(blog);

		// then
		Blog saved = this.blogRepository.findOne(blog.getEntityId());

		assertThat(saved).as("saved blog").isNotNull();
		assertThat(saved.getName()).isEqualTo(blogName);
	}

	@Test(dataProvider = "invalidBlogs")
	public void shouldNotSaveInvalidBlog(String blogName) {
		// given
		Blog blog = this.blogBuilder.withName(blogName).build();

		// when
		catchException(this.blogRepository).saveAndFlush(blog);

		// then
		assertThat(caughtException()).isInstanceOf(DataIntegrityViolationException.class);
	}

	@Test(dataProvider = "validBloggers")
	public void shouldSaveBlogWithValidBlogger(String firstName, String lastName) {
		// given
		Blogger blogger = this.bloggerBuilder.withFirstName(firstName).withLastName(lastName).build();
		Blog blog = this.blogBuilder.withName(ANY_BLOG_NAME).withBlogger(blogger).build();

		// when
		this.blogRepository.saveAndFlush(blog);

		// then
		Blog saved = this.blogRepository.findOne(blog.getEntityId());

		assertThat(saved).as("saved blog").isNotNull();
		assertThat(saved.getBlogger()).isEqualTo(blogger);
	}

	@Test(dataProvider = "invalidBloggers")
	public void shouldNotSaveBlogWithInvalidBlogger(String firstName, String lastName) {
		// given
		Blogger blogger = this.bloggerBuilder.withFirstName(firstName).withLastName(lastName).build();
		Blog blog = this.blogBuilder.withName(ANY_BLOG_NAME).withBlogger(blogger).build();

		// when
		catchException(this.blogRepository).saveAndFlush(blog);

		// then
		assertThat(caughtException()).isInstanceOf(DataIntegrityViolationException.class);
	}

}
