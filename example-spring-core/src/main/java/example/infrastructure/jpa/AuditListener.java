package example.infrastructure.jpa;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import example.domain.shared.audit.Auditable;
import example.domain.shared.date.DateTimeProvider;
import example.domain.shared.security.AuthenticatedUser;
import example.domain.shared.security.AuthenticatedUserProvider;

@Component
public class AuditListener {

	private static DateTimeProvider dateTimeProvider;

	private static AuthenticatedUserProvider authenticatedUserProvider;

	@edu.umd.cs.findbugs.annotations.SuppressWarnings("ST_WRITE_TO_STATIC_FROM_INSTANCE_METHOD")
	@Autowired
	public void setDateTimeProvider(DateTimeProvider dateTimeProvider) {
		AuditListener.dateTimeProvider = dateTimeProvider;
	}

	@edu.umd.cs.findbugs.annotations.SuppressWarnings("ST_WRITE_TO_STATIC_FROM_INSTANCE_METHOD")
	@Autowired
	public void setUserProvider(AuthenticatedUserProvider authenticatedUserProvider) {
		AuditListener.authenticatedUserProvider = authenticatedUserProvider;
	}

	@PrePersist
	@PreUpdate
	public void updateAudit(Object o) {
		if (o instanceof Auditable) {
			Auditable auditable = (Auditable) o;

			AuthenticatedUser userDetails = authenticatedUserProvider.authenticated();
			String modifier = (userDetails != null) ? userDetails.getEmail() : "N/A";

			auditable.updateAudit(dateTimeProvider.currentDateTime(), modifier);
		}
	}
}
