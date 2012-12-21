package example.infrastructure.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import example.TestGroups;

@ContextConfiguration(locations = "classpath:/META-INF/spring/testContext-infrastructure-jpa.xml")
@Test(groups = { TestGroups.INTEGRATION })
@ActiveProfiles("test")
public abstract class AbstractJpaRepositoryTest extends AbstractTransactionalTestNGSpringContextTests {

	@PersistenceContext
	protected EntityManager entityManager;

}
