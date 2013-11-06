package example.application.query;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QTestUser is a Querydsl query type for QTestUser
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QTestUser extends com.mysema.query.sql.RelationalPathBase<QTestUser> {

    private static final long serialVersionUID = -483301850;

    public static final QTestUser testUser = new QTestUser("TEST_USER");

    public final StringPath name = createString("NAME");

    public final com.mysema.query.sql.PrimaryKey<QTestUser> constraint6 = createPrimaryKey(name);

    public QTestUser(String variable) {
        super(QTestUser.class, forVariable(variable), "PUBLIC", "TEST_USER");
    }

    public QTestUser(Path<? extends QTestUser> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "TEST_USER");
    }

    public QTestUser(PathMetadata<?> metadata) {
        super(QTestUser.class, metadata, "PUBLIC", "TEST_USER");
    }

}

