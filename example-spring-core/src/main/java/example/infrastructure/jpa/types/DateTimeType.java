package example.infrastructure.jpa.types;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.usertype.UserType;
import org.joda.time.DateTime;

public class DateTimeType extends AbstractCustomType implements UserType {

	public int[] sqlTypes() {
		return new int[] { Types.TIMESTAMP };
	}

	@Override
	public Class<DateTime> returnedClass() {
		return DateTime.class;
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

}
