package example.infrastructure.jpa;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.joda.time.DateTime;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import example.TestGroups;
import example.domain.shared.audit.Audit;
import example.domain.shared.audit.AuditIdentity;
import example.domain.shared.audit.Auditable;
import example.domain.shared.audit.Auditor;
import example.domain.shared.ddd.Entity;

@ContextConfiguration(locations = "classpath:/META-INF/spring/testContext-infrastructure-jpa.xml")
@Test(groups = { TestGroups.INTEGRATION }, singleThreaded = true)
@ActiveProfiles("test")
public abstract class AbstractJpaRepositoryTest extends AbstractTransactionalTestNGSpringContextTests {

	public static final Audit EXPECTED_AUDIT = Audit.NULL.update(new DateTime(), new AuditIdentity("any identity",
			"any details"));

	@PersistenceContext
	protected EntityManager entityManager;

	@Autowired
	private Auditor auditor;

	@BeforeClass
	public void initializeAuditor() {
		doAnswer(new Answer<Void>() {

			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Object[] arguments = invocation.getArguments();
				Auditable auditable = (Auditable) arguments[0];

				auditable.updateAudit(EXPECTED_AUDIT.getCreationDate(), EXPECTED_AUDIT.getCreator());

				return null;
			}

		}).when(auditor).applyOn(any(Auditable.class));
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
