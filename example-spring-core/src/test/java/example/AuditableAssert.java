package example;

import org.fest.assertions.GenericAssert;

import example.domain.shared.audit.Audit;
import example.domain.shared.audit.Auditable;

public class AuditableAssert extends GenericAssert<AuditableAssert, Auditable> {

	public static AuditableAssert assertThat(Auditable actual) {
		return new AuditableAssert(actual);
	}

	public AuditableAssert(Auditable actual) {
		super(AuditableAssert.class, actual);
	}

	public AuditableAssert hasAudit(Audit audit) {
		org.fest.assertions.Assertions.assertThat(actual.getAudit()).isEqualTo(audit);

		return this;
	}

}