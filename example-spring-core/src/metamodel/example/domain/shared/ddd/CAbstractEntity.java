package example.domain.shared.ddd;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * CAbstractEntity is a Querydsl query type for AbstractEntity
 */
@Generated("com.mysema.query.codegen.SupertypeSerializer")
public class CAbstractEntity extends EntityPathBase<AbstractEntity> {

    private static final long serialVersionUID = -1490430574;

    public static final CAbstractEntity abstractEntity = new CAbstractEntity("abstractEntity");

    public final NumberPath<Long> entityId = createNumber("entityId", Long.class);

    public CAbstractEntity(String variable) {
        super(AbstractEntity.class, forVariable(variable));
    }

    public CAbstractEntity(Path<? extends AbstractEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public CAbstractEntity(PathMetadata<?> metadata) {
        super(AbstractEntity.class, metadata);
    }

}

