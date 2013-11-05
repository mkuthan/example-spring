package example.infrastructure.jpa.types;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.StringType;
import org.hibernate.type.TextType;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import example.domain.shared.json.JsonHolder;
import example.domain.shared.json.JsonSerializer;

@Component
public class JsonHolderType extends AbstractCustomType implements CompositeUserType {

	private static JsonSerializer jsonSerializer;

	@edu.umd.cs.findbugs.annotations.SuppressWarnings("ST_WRITE_TO_STATIC_FROM_INSTANCE_METHOD")
	@Autowired
	void setJsonSerializationService(JsonSerializer jsonSerializer) {
		JsonHolderType.jsonSerializer = jsonSerializer;
	}

	@Override
	public String[] getPropertyNames() {
		return new String[] { "type", "data" };
	}

	@Override
	public Type[] getPropertyTypes() {
		return new Type[] { StringType.INSTANCE, TextType.INSTANCE };
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class<JsonHolder> returnedClass() {
		return JsonHolder.class;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object getPropertyValue(Object component, int propertyIndex) throws HibernateException {
		if (component == null) {
			return null;
		}

		JsonHolder jsonHolder = (JsonHolder) component;
		if (!jsonHolder.hasValue()) {
			return null;
		}

		switch (propertyIndex) {
		case 0:
			return jsonHolder.getValue().getClass().getName();
		case 1:
			return jsonSerializer.toJson(jsonHolder.getValue());
		default:
			throw new HibernateException("Invalid property index [" + propertyIndex + "]");
		}
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner)
			throws HibernateException, SQLException {

		String type = StringType.INSTANCE.nullSafeGet(rs, names[0], session);
		String data = TextType.INSTANCE.nullSafeGet(rs, names[1], session);

		if (type == null && data == null) {
			return null;
		} else {
			Object json = jsonSerializer.fromJson(data, type);
			return JsonHolder.of(json);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session)
			throws HibernateException, SQLException {
		JsonHolder jsonHolder = (JsonHolder) value;

		if (jsonHolder != null && jsonHolder.getValue() != null) {
			StringType.INSTANCE.set(st, jsonHolder.getValue().getClass().getName(), index, session);
			TextType.INSTANCE.set(st, jsonSerializer.toJson(jsonHolder.getValue()), index + 1, session);
		} else {
			StringType.INSTANCE.set(st, null, index, session);
			TextType.INSTANCE.set(st, null, index + 1, session);
		}
	}

}
