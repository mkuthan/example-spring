package example.domain.example;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.persistence.Column;
import javax.persistence.Embedded;

import org.hibernate.annotations.Columns;
import org.joda.money.Money;
import org.joda.time.DateTime;

import example.ddd.AbstractAggregateEntity;
import example.ddd.DomainEntity;
import example.domain.shared.audit.Audit;
import example.domain.shared.audit.Auditable;
import example.domain.shared.json.JsonHolder;

@DomainEntity
public class ExampleEntity extends AbstractAggregateEntity implements Auditable {

	public static final int NAME_MAX_LENGTH = 100;

	@Column(nullable = true, unique = true, length = NAME_MAX_LENGTH)
	private String name;

	@Embedded
	private ExampleValueObject embeddedValueObject;

	@Columns(columns = { @Column(name = "json_type"), @Column(name = "json_value") })
	private JsonHolder<ExampleValueObject> jsonValueObject = JsonHolder.absent();

	@Column
	private DateTime dateTime;

	@Columns(columns = { @Column(name = "amount"), @Column(name = "currency") })
	private Money money;
	
	@Embedded
	private Audit audit = Audit.DEFAULT;

	protected ExampleEntity() {
	}

	public ExampleEntity(String name) {
		this.name = checkNotNull(name);
	}

	public String getName() {
		return name;
	}

	public ExampleValueObject getValue() {
		return embeddedValueObject;
	}

	public void setEmbeddedValueObject(ExampleValueObject embeddedValueObject) {
		this.embeddedValueObject = embeddedValueObject;
	}

	public ExampleValueObject getJson() {
		return jsonValueObject.getValue();
	}

	public void setJsonValueObject(ExampleValueObject jsonValueObject) {
		this.jsonValueObject = JsonHolder.of(jsonValueObject);
	}

	public DateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(DateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Money getMoney() {
		return money;
	}

	public void setMoney(Money money) {
		this.money = money;
	}

	@Override
	public Audit getAudit() {
		return audit;
	}

	@Override
	public void updateAudit(DateTime now, String modifier) {
		audit = audit.update(now, modifier);
	}

	public static class Builder {

		private String name;

		private ExampleValueObject embeddedValueObject;

		private ExampleValueObject jsonValueObject;

		private DateTime dateTime;

		private Money money;

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withEmbeddedValueObject(ExampleValueObject embeddedValueObject) {
			this.embeddedValueObject = embeddedValueObject;
			return this;
		}

		public Builder withJsonValueObject(ExampleValueObject jsonValueObject) {
			this.jsonValueObject = jsonValueObject;
			return this;
		}

		public Builder withDateTime(DateTime dateTime) {
			this.dateTime = dateTime;
			return this;
		}

		public Builder withMoney(Money money) {
			this.money = money;
			return this;
		}

		public ExampleEntity build() {
			ExampleEntity exampleEntity = new ExampleEntity(name);

			exampleEntity.setEmbeddedValueObject(embeddedValueObject);
			exampleEntity.setJsonValueObject(jsonValueObject);
			exampleEntity.setDateTime(dateTime);
			exampleEntity.setMoney(money);

			return exampleEntity;
		}
	}

}
