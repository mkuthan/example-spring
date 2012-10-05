package example.domain.model.blog;

import static com.google.common.base.Preconditions.*;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.google.common.base.Objects;

import example.domain.model.account.Account;
import example.domain.shared.DomainValueObject;

@DomainValueObject
public class Blogger {

	public static final int FIRST_NAME_MAX_LENGTH = 50;
	public static final int LAST_NAME_MAX_LENGTH = 50;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private Account account;

	@Column(length = FIRST_NAME_MAX_LENGTH)
	private String firstName;

	@Column(length = LAST_NAME_MAX_LENGTH)
	private String lastName;

	protected Blogger() {
	}

	public Blogger(Account blogger) {
		this.account = checkNotNull(blogger);
	}

	public Account getAccount() {
		return this.account;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		Blogger other = (Blogger) obj;
		return Objects.equal(this.account, other.account) && Objects.equal(this.firstName, other.firstName)
				&& Objects.equal(this.lastName, other.lastName);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.account, this.firstName, this.lastName);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).addValue(this.account).addValue(this.firstName).addValue(this.lastName)
				.toString();
	}

}
