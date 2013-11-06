package example.domain.shared.ddd;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * CAbstractAggregateEntity is a Querydsl query type for AbstractAggregateEntity
 */
@Generated("com.mysema.query.codegen.SupertypeSerializer")
public class CAbstractAggregateEntity extends EntityPathBase<AbstractAggregateEntity> {

    private static final long serialVersionUID = 206554547;

    public static final CAbstractAggregateEntity abstractAggregateEntity = new CAbstractAggregateEntity("abstractAggregateEntity");

    public final CAbstractEntity _super = new CAbstractEntity(this);

    //inherited
    public final NumberPath<Long> entityId = _super.entityId;

    public final NumberPath<Integer> entityVersion = createNumber("entityVersion", Integer.class);

    public CAbstractAggregateEntity(String variable) {
        super(AbstractAggregateEntity.class, forVariable(variable));
    }

    public CAbstractAggregateEntity(Path<? extends AbstractAggregateEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public CAbstractAggregateEntity(PathMetadata<?> metadata) {
        super(AbstractAggregateEntity.class, metadata);
    }

}

