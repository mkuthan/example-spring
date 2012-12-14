package example.domain.blog;

public class BlogBuilder {

	private String name;

	private Blogger blogger;

	public BlogBuilder withName(String name) {
		this.name = name;
		return this;
	}

	public BlogBuilder withBlogger(Blogger blogger) {
		this.blogger = blogger;
		return this;
	}

	public Blog build() {
		Blog blog = new Blog(name, blogger);

		return blog;
	}
}
