package example.domain.model.blog;

import static com.google.common.base.Preconditions.*;

import javax.persistence.Column;
import javax.persistence.Embedded;

import com.google.common.base.Objects;

import example.domain.shared.AbstractAggregateEntity;
import example.domain.shared.DomainEntity;

@DomainEntity
public class Blog extends AbstractAggregateEntity<Blog> {

	public static final int NAME_MAX_LENGTH = 50;

	@Column(nullable = true, unique = true, length = NAME_MAX_LENGTH)
	private String name;

	@Embedded
	private Blogger blogger;

	protected Blog() {
	}

	public Blog(String name, Blogger blogger) {
		this.name = checkNotNull(name);
		this.blogger = checkNotNull(blogger);
	}

	public Blogger getBlogger() {
		return blogger;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).addValue(name).addValue(blogger).toString();
	}

}
