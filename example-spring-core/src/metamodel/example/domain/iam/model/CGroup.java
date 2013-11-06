package example.domain.iam.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * CGroup is a Querydsl query type for Group
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class CGroup extends EntityPathBase<Group> {

    private static final long serialVersionUID = 966511899;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final CGroup group = new CGroup("group1");

    public final example.domain.shared.ddd.CAbstractAggregateEntity _super = new example.domain.shared.ddd.CAbstractAggregateEntity(this);

    //inherited
    public final NumberPath<Long> entityId = _super.entityId;

    //inherited
    public final NumberPath<Integer> entityVersion = _super.entityVersion;

    public final StringPath name = createString("name");

    public final CGroup parent;

    public final SetPath<Group, CGroup> subGroups = this.<Group, CGroup>createSet("subGroups", Group.class, CGroup.class, PathInits.DIRECT2);

    public CGroup(String variable) {
        this(Group.class, forVariable(variable), INITS);
    }

    public CGroup(Path<? extends Group> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public CGroup(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public CGroup(PathMetadata<?> metadata, PathInits inits) {
        this(Group.class, metadata, inits);
    }

    public CGroup(Class<? extends Group> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parent = inits.isInitialized("parent") ? new CGroup(forProperty("parent"), inits.get("parent")) : null;
    }

}

