package example.domain.shared.audit;

import org.joda.time.DateTime;

public interface Auditable {

	Audit getAudit();

	void updateAudit(DateTime now, String modifier);

}
