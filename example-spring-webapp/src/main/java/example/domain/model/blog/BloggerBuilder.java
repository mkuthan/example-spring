package example.domain.model.blog;

import example.domain.model.account.Account;

public class BloggerBuilder {

	private Account account;

	private String firstName;

	private String lastName;

	public BloggerBuilder withAccount(Account account) {
		this.account = account;
		return this;
	}

	public BloggerBuilder withFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public BloggerBuilder withLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public Blogger build() {
		return new Blogger(account, firstName, lastName);
	}

}
