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

	public Blogger(Account blogger, String firstName, String lastName) {
		account = checkNotNull(blogger);
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Account getAccount() {
		return account;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
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
		return Objects.equal(account, other.account) && Objects.equal(firstName, other.firstName)
				&& Objects.equal(lastName, other.lastName);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(account, firstName, lastName);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).addValue(account).addValue(firstName).addValue(lastName).toString();
	}

}
