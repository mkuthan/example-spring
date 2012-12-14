package example.domain.post;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;

import example.domain.blog.Blog;
import example.domain.shared.ddd.DomainRepository;

public interface PostRepository extends DomainRepository<Post> {

	Page<Post> findByBlog(Blog blog, Pageable pageable);

}
