package example.infrastructure.jpa;

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
public class TestEntity extends AbstractAggregateEntity implements Auditable {

	@Column(nullable = true, unique = true)
	private String name;

	@Embedded
	private TestValueObject testValueObjectEmbedded;

	@Columns(columns = { @Column(name = "test_value_object_json_class"), @Column(name = "test_value_object_json_value") })
	private JsonHolder<TestValueObject> testValueObjectHolder = JsonHolder.absent();

	@Column
	private DateTime dateTime;

	@Columns(columns = { @Column(name = "amount"), @Column(name = "currency") })
	private Money money;

	@Embedded
	private Audit audit = Audit.NULL;

	protected TestEntity() {
	}

	public TestEntity(String name) {
		this.name = checkNotNull(name);
	}

	public String getName() {
		return name;
	}

	public TestValueObject getTestValueObjectEmbedded() {
		return testValueObjectEmbedded;
	}

	public void setTestValueObjectEmbedded(TestValueObject testValueObjectEmbedded) {
		this.testValueObjectEmbedded = testValueObjectEmbedded;
	}

	public TestValueObject getTestValueObjectJson() {
		return testValueObjectHolder.getValue();
	}

	public void setTestValueObjectJson(TestValueObject testValueObjectJson) {
		this.testValueObjectHolder = JsonHolder.of(testValueObjectJson);
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

}
