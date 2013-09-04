package example.infrastructure.jpa;

import org.hibernate.cfg.ImprovedNamingStrategy;

public class FixedPrefixNamingStrategy extends ImprovedNamingStrategy {

	private static final long serialVersionUID = 1L;

	private static final String TABLE_PREFIX = "t_";

	private static final String COLUMN_PREFIX = "c_";

	@Override
	public String classToTableName(String className) {
		return TABLE_PREFIX + super.classToTableName(className);
	}

	@Override
	public String collectionTableName(String ownerEntity, String ownerEntityTable, String associatedEntity,
			String associatedEntityTable, String propertyName) {
		return TABLE_PREFIX
				+ super.collectionTableName(ownerEntity, ownerEntityTable, associatedEntity, associatedEntityTable,
						propertyName);
	}

	@Override
	public String propertyToColumnName(String propertyName) {
		return COLUMN_PREFIX + super.propertyToColumnName(propertyName);
	}

	@Override
	public String joinKeyColumnName(String joinedColumn, String joinedTable) {
		return COLUMN_PREFIX + super.joinKeyColumnName(joinedColumn, joinedTable);
	}

	@Override
	public String columnName(String columnName) {
		return COLUMN_PREFIX + super.columnName(columnName);
	}
}