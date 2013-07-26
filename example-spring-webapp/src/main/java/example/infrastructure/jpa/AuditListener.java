package example.infrastructure.jpa;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import example.security.domain.User;
import example.security.domain.UserProvider;
import example.shared.audit.Auditable;
import example.shared.date.DateTimeProvider;

@Component
public class AuditListener {

	private static DateTimeProvider dateTimeProvider;

	private static UserProvider acountProvider;

	@edu.umd.cs.findbugs.annotations.SuppressWarnings("ST_WRITE_TO_STATIC_FROM_INSTANCE_METHOD")
	@Autowired
	public void setDateTimeProvider(DateTimeProvider dateTimeProvider) {
		AuditListener.dateTimeProvider = dateTimeProvider;
	}

	@edu.umd.cs.findbugs.annotations.SuppressWarnings("ST_WRITE_TO_STATIC_FROM_INSTANCE_METHOD")
	@Autowired
	public void setUserProvider(UserProvider userProvider) {
		AuditListener.acountProvider = userProvider;
	}

	@PrePersist
	@PreUpdate
	public void updateAudit(Object o) {
		if (o instanceof Auditable) {
			Auditable auditable = (Auditable) o;

			User authenticated = acountProvider.authenticated();
			String modifier = (authenticated != null) ? authenticated.getEmail() : "N/A";

			auditable.updateAudit(dateTimeProvider.now(), modifier);
		}
	}
}
