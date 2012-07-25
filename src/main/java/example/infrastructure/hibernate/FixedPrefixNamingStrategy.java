package example.infrastructure.hibernate;

import org.hibernate.annotations.common.util.StringHelper;
import org.hibernate.cfg.ImprovedNamingStrategy;

public class FixedPrefixNamingStrategy extends ImprovedNamingStrategy {

    private final static String TABLE_PREFIX = "t_";

    private final static String COLUMN_PREFIX = "c_";

    @Override
    public String classToTableName(String className) {
	return TABLE_PREFIX + StringHelper.unqualify(className);
    }

    @Override
    public String propertyToColumnName(String propertyName) {
	return COLUMN_PREFIX + StringHelper.unqualify(propertyName);
    }
}