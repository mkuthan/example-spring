package example.infrastructure.jpa.types;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;

import com.google.common.base.Objects;

public abstract class AbstractCustomType {

	public void setPropertyValue(Object component, int propertyIndex, Object value) throws HibernateException {
		throw new HibernateException("Called setPropertyValue on an immutable type {" + component.getClass() + "}");
	}

	@SuppressWarnings("PMD.SuspiciousEqualsMethodName")
	public boolean equals(Object x, Object y) throws HibernateException {
		return Objects.equal(x, y);
	}

	public int hashCode(Object x) throws HibernateException {
		return Objects.hashCode(x);
	}

	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	public boolean isMutable() {
		return false;
	}

	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	public Serializable disassemble(Object value, SessionImplementor session) throws HibernateException {
		return (Serializable) value;
	}

	public Object assemble(Serializable cached, SessionImplementor session, Object owner) throws HibernateException {
		return cached;
	}

	public Object assemble(Serializable cached, Object value) throws HibernateException {
		return cached;
	}

	public Object replace(Object original, Object target, SessionImplementor session, Object owner)
			throws HibernateException {
		return original;
	}

	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}

}
