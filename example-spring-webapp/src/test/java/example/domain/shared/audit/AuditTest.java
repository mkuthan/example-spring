package example.domain.shared.audit;

import static org.fest.assertions.Assertions.assertThat;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

import example.TestGroups;

@Test(groups = TestGroups.UNIT)
public class AuditTest {

	@Test
	public void shouldUpdateDefaultInstance() {
		// given
		DateTime now = new DateTime();
		String creator = "creator";

		// when
		Audit audit = Audit.DEFAULT.update(now, creator);

		// then

		assertThat(audit.getCreator()).isEqualTo(creator);
		assertThat(audit.getModifier()).isEqualTo(creator);

		assertThat(audit.getCreationDate()).isEqualTo(now);
		assertThat(audit.getModificationDate()).isEqualTo(now);
	}

	@Test
	public void shouldUpdateExistingInstance() {
		// given
		DateTime creationDate = new DateTime("2010-01-01");
		String creator = "creator";

		DateTime modificationDate = new DateTime("2011-01-01");
		String modifier = "modifier";

		// when
		Audit audit = Audit.DEFAULT.update(creationDate, creator).update(modificationDate, modifier);

		// then

		assertThat(audit.getCreator()).isEqualTo(creator);
		assertThat(audit.getModifier()).isEqualTo(modifier);

		assertThat(audit.getCreationDate()).isEqualTo(creationDate);
		assertThat(audit.getModificationDate()).isEqualTo(modificationDate);
	}

}
