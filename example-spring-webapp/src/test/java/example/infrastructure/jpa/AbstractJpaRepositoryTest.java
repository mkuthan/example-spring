package example.infrastructure.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import example.TestGroups;

@Test(groups = { TestGroups.JPA })
@ContextConfiguration(locations = "classpath:/META-INF/spring/testContext-infrastructure-jpa.xml")
public abstract class AbstractJpaRepositoryTest extends AbstractTransactionalTestNGSpringContextTests {

	@PersistenceContext
	protected EntityManager entityManager;

}
