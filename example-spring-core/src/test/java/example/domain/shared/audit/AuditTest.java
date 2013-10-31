package example.domain.shared.audit;

import static org.fest.assertions.Assertions.assertThat;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

import example.TestGroups;

@Test(groups = TestGroups.UNIT)
public class AuditTest {

	@Test
	public void shouldUpdateNullInstance() {
		// given
		DateTime now = new DateTime();
		AuditIdentity creator = new AuditIdentity("creator", "creator");

		Audit audit = Audit.NULL;

		// when
		Audit updatedAudit = audit.update(now, creator);

		// then
		assertThat(updatedAudit.getCreator()).isEqualTo(creator);
		assertThat(updatedAudit.getCreationDate()).isEqualTo(now);

		assertThat(updatedAudit.getModifier()).isEqualTo(creator);
		assertThat(updatedAudit.getModificationDate()).isEqualTo(now);
	}

	@Test
	public void shouldUpdateExistingInstance() {
		// given
		DateTime creationDate = new DateTime("2010-01-01");
		AuditIdentity creator = new AuditIdentity("creator", "creator");

		DateTime modificationDate = new DateTime("2011-01-01");
		AuditIdentity modifier = new AuditIdentity("modifier", "modifier");

		Audit audit = new Audit(creationDate, creator, creationDate, creator);

		// when
		Audit updatedAudit = audit.update(modificationDate, modifier);

		// then
		assertThat(updatedAudit.getCreator()).isEqualTo(creator);
		assertThat(updatedAudit.getCreationDate()).isEqualTo(creationDate);

		assertThat(updatedAudit.getModifier()).isEqualTo(modifier);
		assertThat(updatedAudit.getModificationDate()).isEqualTo(modificationDate);
	}

}
