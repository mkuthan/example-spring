package example.infrastructure.jpa;

import static org.mockito.Mockito.*;

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
import example.ddd.Entity;
import example.domain.shared.audit.Audit;
import example.domain.shared.date.DateTimeProvider;
import example.domain.shared.security.Account;
import example.domain.shared.security.AccountProvider;

@ContextConfiguration(locations = "classpath:/META-INF/spring/testContext-jpa.xml")
@Test(groups = { TestGroups.INTEGRATION })
@ActiveProfiles("test")
public abstract class AbstractJpaRepositoryTest extends AbstractTransactionalTestNGSpringContextTests {

	public static final DateTime AUDIT_DATE_TIME = new DateTime();

	public static final Account AUDIT_ACCOUNT = new Account("ANY USER");

	public static final Audit EXPECTED_AUDIT = new Audit(AUDIT_DATE_TIME, AUDIT_DATE_TIME, AUDIT_ACCOUNT.getUsername(),
			AUDIT_ACCOUNT.getUsername());

	@PersistenceContext
	protected EntityManager entityManager;

	@Autowired
	private DateTimeProvider dateTimeProvider;

	@Autowired
	private AccountProvider accountProvider;

	@BeforeClass
	public void initializeDateProvider() {
		when(dateTimeProvider.now()).thenReturn(AUDIT_DATE_TIME);
	}

	@BeforeClass
	public void initializeAccountProvider() {
		when(accountProvider.authenticated()).thenReturn(AUDIT_ACCOUNT);
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
