package example.domain.shared.audit;

import static com.google.common.base.Preconditions.checkNotNull;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;

import example.domain.shared.date.DateTimeProvider;
import example.domain.shared.security.AuthenticatedUser;
import example.domain.shared.security.AuthenticatedUserProvider;

@Component
public class Auditor {

	private AuthenticatedUserProvider authenticatedUserProvider;

	private DateTimeProvider dateTimeProvider;

	@Autowired
	public Auditor(AuthenticatedUserProvider authenticatedUserProvider, DateTimeProvider dateTimeProvider) {
		this.authenticatedUserProvider = authenticatedUserProvider;
		this.dateTimeProvider = dateTimeProvider;
	}

	public void applyOn(Auditable auditable) {
		checkNotNull(auditable);

		DateTime modificationDate = getModificationDate();
		AuditIdentity modifer = getModifier();

		auditable.updateAudit(modificationDate, modifer);
	}

	private DateTime getModificationDate() {
		DateTime now = dateTimeProvider.currentDateTime();
		return now;
	}

	private AuditIdentity getModifier() {
		Optional<AuthenticatedUser> authenticatedUser = authenticatedUserProvider.authenticatedUser();
		AuditIdentity modifer = AuditIdentity.NOT_AVAILABLE;
		if (authenticatedUser.isPresent()) {
			modifer = new AuditIdentity(authenticatedUser.get().getUsername(), authenticatedUser.get().getFullname());
		}
		return modifer;
	}

}