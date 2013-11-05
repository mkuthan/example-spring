package example.infrastructure.jpa.types;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

public class MoneyType extends AbstractCustomType implements CompositeUserType {

	@Override
	public String[] getPropertyNames() {
		return new String[] { "amount", "currency" };
	}

	@Override
	public Type[] getPropertyTypes() {
		return new Type[] { BigDecimalType.INSTANCE, StringType.INSTANCE };
	}

	@Override
	public Class<Money> returnedClass() {
		return Money.class;
	}

	@Override
	public Object getPropertyValue(Object component, int propertyIndex) throws HibernateException {
		if (component == null) {
			return null;
		}
		final Money money = (Money) component;

		switch (propertyIndex) {
		case 0:
			return money.getAmount();
		case 1:
			return money.getCurrencyUnit().getCurrencyCode();
		default:
			throw new HibernateException("Invalid property index [" + propertyIndex + "]");
		}
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner)
			throws HibernateException, SQLException {

		BigDecimal amount = BigDecimalType.INSTANCE.nullSafeGet(rs, names[0], session);
		String currencyCode = StringType.INSTANCE.nullSafeGet(rs, names[1], session);

		return amount == null && currencyCode == null ? null : Money.of(CurrencyUnit.getInstance(currencyCode), amount);
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session)
			throws HibernateException, SQLException {
		if (value == null) {
			BigDecimalType.INSTANCE.set(st, null, index, session);
			StringType.INSTANCE.set(st, null, index + 1, session);
		} else {
			Money money = (Money) value;
			BigDecimalType.INSTANCE.set(st, money.getAmount(), index, session);
			StringType.INSTANCE.set(st, money.getCurrencyUnit().getCurrencyCode(), index + 1, session);
		}
	}

}
