package example.domain.iam.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * CUser is a Querydsl query type for User
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class CUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1555616175;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final CUser user = new CUser("user");

    public final example.domain.shared.ddd.CAbstractAggregateEntity _super = new example.domain.shared.ddd.CAbstractAggregateEntity(this);

    public final StringPath email = createString("email");

    public final BooleanPath enabled = createBoolean("enabled");

    //inherited
    public final NumberPath<Long> entityId = _super.entityId;

    //inherited
    public final NumberPath<Integer> entityVersion = _super.entityVersion;

    public final StringPath firstname = createString("firstname");

    public final StringPath fullname = createString("fullname");

    public final SetPath<Group, CGroup> groups = this.<Group, CGroup>createSet("groups", Group.class, CGroup.class, PathInits.DIRECT2);

    public final CUserIdentifier identifier;

    public final StringPath lastname = createString("lastname");

    public CUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public CUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public CUser(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public CUser(PathMetadata<?> metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public CUser(Class<? extends User> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.identifier = inits.isInitialized("identifier") ? new CUserIdentifier(forProperty("identifier")) : null;
    }

}

