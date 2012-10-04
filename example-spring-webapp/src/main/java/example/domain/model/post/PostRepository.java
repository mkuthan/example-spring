package example.domain.model.post;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;

import example.domain.model.blog.Blog;
import example.domain.shared.DomainRepository;

public interface PostRepository extends DomainRepository<Post> {

    Page<Post> findByBlog(Blog blog, Pageable pageable);

}
