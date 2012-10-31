package example.infrastructure.jpa;

import org.hibernate.annotations.common.util.StringHelper;
import org.hibernate.cfg.ImprovedNamingStrategy;

public class FixedPrefixNamingStrategy extends ImprovedNamingStrategy {

	private static final long serialVersionUID = 1L;

	private static final String TABLE_PREFIX = "t_";

	private static final String COLUMN_PREFIX = "c_";

	@Override
	public String classToTableName(String className) {
		return TABLE_PREFIX + StringHelper.unqualify(className);
	}

	@Override
	public String propertyToColumnName(String propertyName) {
		return COLUMN_PREFIX + StringHelper.unqualify(propertyName);
	}
}