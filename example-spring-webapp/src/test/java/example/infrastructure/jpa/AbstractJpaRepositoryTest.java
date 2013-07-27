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
import example.shared.audit.Audit;
import example.shared.date.DateTimeProvider;
import example.shared.ddd.Entity;
import example.shared.security.AuthenticatedUserDetails;
import example.shared.security.AuthenticatedUserDetailsProvider;

@ContextConfiguration(locations = "classpath:/META-INF/spring/testContext-jpa.xml")
@Test(groups = { TestGroups.INTEGRATION }, singleThreaded = true)
@ActiveProfiles("test")
public abstract class AbstractJpaRepositoryTest extends AbstractTransactionalTestNGSpringContextTests {

	public static final DateTime AUDIT_DATE_TIME = new DateTime();

	public static final AuthenticatedUserDetails AUDIT_USER_DETAILS = new TestUserDetails("any@domain.com");

	public static final Audit EXPECTED_AUDIT = new Audit(AUDIT_DATE_TIME, AUDIT_DATE_TIME,
			AUDIT_USER_DETAILS.getEmail(), AUDIT_USER_DETAILS.getEmail());

	@PersistenceContext
	protected EntityManager entityManager;

	@Autowired
	private DateTimeProvider dateTimeProvider;

	@Autowired
	private AuthenticatedUserDetailsProvider authenticatedUserDetailsProvider;

	@BeforeClass
	public void initializeDateProvider() {
		when(dateTimeProvider.now()).thenReturn(AUDIT_DATE_TIME);
	}

	@BeforeClass
	public void initializeAccountProvider() {
		when(authenticatedUserDetailsProvider.authenticated()).thenReturn(AUDIT_USER_DETAILS);
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

	private static class TestUserDetails implements AuthenticatedUserDetails {

		private String email;

		public TestUserDetails(String email) {
			this.email = email;
		}

		@Override
		public String getUsername() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String getEmail() {
			return email;
		}

		@Override
		public String getFirstname() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String getLastname() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String getFullname() {
			throw new UnsupportedOperationException();
		}

	}

}
