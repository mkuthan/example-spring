package example.domain.shared.audit;

import org.joda.time.DateTime;

public interface Auditable {

	void updateAudit(DateTime modificationDate, AuditIdentity modifier);

	Audit getAudit();

}
