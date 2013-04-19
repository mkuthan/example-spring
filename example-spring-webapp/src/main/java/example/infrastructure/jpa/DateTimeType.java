package example.infrastructure.jpa;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.usertype.UserType;
import org.joda.time.DateTime;

import com.google.common.base.Objects;

public class DateTimeType implements UserType {

	public static final DateTimeType INSTANCE = new DateTimeType();

	private static final int[] SQL_TYPES = new int[] { Types.TIMESTAMP, };

	public int[] sqlTypes() {
		return SQL_TYPES;
	}

	@Override
	public Object nullSafeGet(ResultSet resultSet, String[] names, SessionImplementor session, Object owner)
			throws HibernateException, SQLException {
		Object timestamp = StandardBasicTypes.TIMESTAMP.nullSafeGet(resultSet, names, session, owner);
		if (timestamp == null) {
			return null;
		}

		return new DateTime(timestamp);
	}

	@Override
	public void nullSafeSet(PreparedStatement preparedStatement, Object value, int index, SessionImplementor session)
			throws HibernateException, SQLException {
		if (value == null) {
			StandardBasicTypes.TIMESTAMP.nullSafeSet(preparedStatement, null, index, session);
		} else {
			StandardBasicTypes.TIMESTAMP.nullSafeSet(preparedStatement, ((DateTime) value).toDate(), index, session);
		}

	}

	@Override
	public Class<DateTime> returnedClass() {
		return DateTime.class;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		return Objects.equal(x, y);
	}

	@Override
	public int hashCode(Object x) throws HibernateException {
		return Objects.hashCode(x);
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	@Override
	public Object assemble(Serializable cached, Object value) throws HibernateException {
		return cached;
	}

	@Override
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}

}
