package example.sample.domain;

import static com.google.common.base.Preconditions.*;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import org.hibernate.annotations.Columns;
import org.joda.money.Money;
import org.joda.time.DateTime;

import example.shared.audit.Audit;
import example.shared.audit.Auditable;
import example.shared.ddd.AbstractAggregateEntity;
import example.shared.json.JsonHolder;

@Entity
public class SampleEntity extends AbstractAggregateEntity implements Auditable {

	public static final int NAME_MAX_LENGTH = 100;

	@Column(nullable = true, unique = true, length = NAME_MAX_LENGTH)
	private String name;

	@Embedded
	private SampleValueObject embeddedValueObject;

	@Columns(columns = { @Column(name = "json_type"), @Column(name = "json_value") })
	private JsonHolder<SampleValueObject> jsonValueObject = JsonHolder.absent();

	@Column
	private DateTime dateTime;

	@Columns(columns = { @Column(name = "amount"), @Column(name = "currency") })
	private Money money;

	@Embedded
	private Audit audit = Audit.DEFAULT;

	protected SampleEntity() {
	}

	public SampleEntity(Builder builder) {
		this.name = checkNotNull(builder.name);

		setEmbeddedValueObject(builder.embeddedValueObject);
		setJsonValueObject(builder.jsonValueObject);
		setDateTime(builder.dateTime);
		setMoney(builder.money);
	}

	public String getName() {
		return name;
	}

	public SampleValueObject getValue() {
		return embeddedValueObject;
	}

	public void setEmbeddedValueObject(SampleValueObject embeddedValueObject) {
		this.embeddedValueObject = embeddedValueObject;
	}

	public SampleValueObject getJson() {
		return jsonValueObject.getValue();
	}

	public void setJsonValueObject(SampleValueObject jsonValueObject) {
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

		private SampleValueObject embeddedValueObject;

		private SampleValueObject jsonValueObject;

		private DateTime dateTime;

		private Money money;

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withEmbeddedValueObject(SampleValueObject embeddedValueObject) {
			this.embeddedValueObject = embeddedValueObject;
			return this;
		}

		public Builder withJsonValueObject(SampleValueObject jsonValueObject) {
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

		public SampleEntity build() {
			return new SampleEntity(this);
		}
	}

}
