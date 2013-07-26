package example.infrastructure.jpa;

import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import example.TestGroups;
import example.security.domain.User;
import example.security.domain.UserIdentifier;
import example.security.domain.UserProvider;
import example.shared.audit.Audit;
import example.shared.date.DateTimeProvider;
import example.shared.ddd.Entity;

@ContextConfiguration(locations = "classpath:/META-INF/spring/testContext-jpa.xml")
@Test(groups = { TestGroups.INTEGRATION }, singleThreaded = true)
@ActiveProfiles("test")
public abstract class AbstractJpaRepositoryTest extends AbstractTransactionalTestNGSpringContextTests {

	public static final DateTime AUDIT_DATE_TIME = new DateTime();

	public static final User AUDIT_ACCOUNT = new User.Builder().withIdentifier(new UserIdentifier("any identifier"))
			.withEmail("any@domain.com").withFirstname("any firstname").withLastname("any lastname").build();

	public static final Audit EXPECTED_AUDIT = new Audit(AUDIT_DATE_TIME, AUDIT_DATE_TIME, AUDIT_ACCOUNT.getEmail(),
			AUDIT_ACCOUNT.getEmail());

	@PersistenceContext
	protected EntityManager entityManager;

	@Autowired
	private DateTimeProvider dateTimeProvider;

	@Autowired
	private UserProvider userProvider;

	@BeforeClass
	public void initializeDateProvider() {
		when(dateTimeProvider.now()).thenReturn(AUDIT_DATE_TIME);
	}

	@BeforeClass
	public void initializeAccountProvider() {
		when(userProvider.authenticated()).thenReturn(AUDIT_ACCOUNT);
	}

	protected <T extends Entity> T saveFlushAndClear(T entity) {
		entityManager.persist(entity);
		flushAndClear();

		return entity;
	}

	protected void flushAndClear() {
		entityManager.flush();
		entityManager.clear();
	}

}
