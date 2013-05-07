package example.infrastructure.jpa;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import example.domain.shared.audit.Auditable;
import example.domain.shared.date.DateTimeProvider;
import example.domain.shared.security.Account;
import example.domain.shared.security.AccountProvider;

@Component
public class AuditListener {

	private static DateTimeProvider dateTimeProvider;

	private static AccountProvider acountProvider;

	@edu.umd.cs.findbugs.annotations.SuppressWarnings("ST_WRITE_TO_STATIC_FROM_INSTANCE_METHOD")
	@Autowired
	public void setDateTimeProvider(DateTimeProvider dateTimeProvider) {
		AuditListener.dateTimeProvider = dateTimeProvider;
	}

	@edu.umd.cs.findbugs.annotations.SuppressWarnings("ST_WRITE_TO_STATIC_FROM_INSTANCE_METHOD")
	@Autowired
	public void setUserProvider(AccountProvider userProvider) {
		AuditListener.acountProvider = userProvider;
	}

	@PrePersist
	@PreUpdate
	public void updateAudit(Object o) {
		if (o instanceof Auditable) {
			Auditable auditable = (Auditable) o;

			Account authenticated = acountProvider.authenticated();
			String modifier = (authenticated != null) ? authenticated.getUsername() : "N/A";

			auditable.updateAudit(dateTimeProvider.now(), modifier);
		}
	}
}
