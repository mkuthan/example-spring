package example.application.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QSchemaVersion is a Querydsl query type for QSchemaVersion
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QSchemaVersion extends com.mysema.query.sql.RelationalPathBase<QSchemaVersion> {

    private static final long serialVersionUID = 2113142510;

    public static final QSchemaVersion schemaVersion = new QSchemaVersion("schema_version");

    public final NumberPath<Integer> checksum = createNumber("checksum", Integer.class);

    public final StringPath description = createString("description");

    public final NumberPath<Integer> executionTime = createNumber("execution_time", Integer.class);

    public final StringPath installedBy = createString("installed_by");

    public final DateTimePath<java.sql.Timestamp> installedOn = createDateTime("installed_on", java.sql.Timestamp.class);

    public final NumberPath<Integer> installedRank = createNumber("installed_rank", Integer.class);

    public final StringPath script = createString("script");

    public final BooleanPath success = createBoolean("success");

    public final StringPath type = createString("type");

    public final StringPath version = createString("version");

    public final NumberPath<Integer> versionRank = createNumber("version_rank", Integer.class);

    public final com.mysema.query.sql.PrimaryKey<QSchemaVersion> schemaVersionPk = createPrimaryKey(version);

    public QSchemaVersion(String variable) {
        super(QSchemaVersion.class, forVariable(variable), "PUBLIC", "schema_version");
    }

    public QSchemaVersion(Path<? extends QSchemaVersion> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "schema_version");
    }

    public QSchemaVersion(PathMetadata<?> metadata) {
        super(QSchemaVersion.class, metadata, "PUBLIC", "schema_version");
    }

}

