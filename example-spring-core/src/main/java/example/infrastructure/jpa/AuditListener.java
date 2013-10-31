package example.infrastructure.jpa;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import example.domain.shared.audit.Auditable;
import example.domain.shared.audit.Auditor;

@Component
public class AuditListener {

	private static Auditor auditor;

	@edu.umd.cs.findbugs.annotations.SuppressWarnings("ST_WRITE_TO_STATIC_FROM_INSTANCE_METHOD")
	@Autowired
	public void setDateTimeProvider(Auditor auditor) {
		AuditListener.auditor = auditor;
	}

	@PrePersist
	@PreUpdate
	public void updateAudit(Object o) {
		if (o instanceof Auditable) {
			Auditable auditable = (Auditable) o;
			auditor.applyOn(auditable);
		}
	}
}
