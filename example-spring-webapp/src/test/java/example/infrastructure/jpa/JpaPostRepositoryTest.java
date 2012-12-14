package example.infrastructure.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;

import example.domain.post.Post;

public class JpaPostRepositoryTest extends AbstractJpaRepositoryTest {

	@Autowired
	Post postRepository;

	@BeforeMethod
	public void setUp() {
	}

}
