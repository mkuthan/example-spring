package example.domain.model.post;

import static com.google.common.base.Preconditions.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import example.domain.model.blog.Blog;
import example.domain.shared.AbstractAggregateEntity;

@Entity
public class Post extends AbstractAggregateEntity<Post> {

    @ManyToOne(fetch = FetchType.LAZY)
    private Blog blog;

    private String title;

    private String content;

    protected Post() {
    }

    public Post(Blog blog) {
	this.blog = checkNotNull(blog);
    }

    public Blog getBlog() {
	return this.blog;
    }

    public String getTitle() {
	return this.title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getContent() {
	return this.content;
    }

    public void setContent(String content) {
	this.content = content;
    }

}
