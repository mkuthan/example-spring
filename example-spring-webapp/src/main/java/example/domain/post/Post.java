package example.domain.post;

import static com.google.common.base.Preconditions.*;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.joda.time.DateTime;

import example.domain.blog.Blog;
import example.domain.shared.ddd.AbstractAggregateEntity;
import example.domain.shared.ddd.DomainEntity;

@DomainEntity
public class Post extends AbstractAggregateEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	private Blog blog;

	private String title;

	private String content;

	private DateTime created;

	protected Post() {
	}

	public Post(Blog blog) {
		this.blog = checkNotNull(blog);
	}

	public Blog getBlog() {
		return blog;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public DateTime getCreated() {
		return created;
	}

}
