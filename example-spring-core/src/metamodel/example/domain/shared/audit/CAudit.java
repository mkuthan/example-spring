package example.domain.shared.audit;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * CAudit is a Querydsl query type for Audit
 */
@Generated("com.mysema.query.codegen.EmbeddableSerializer")
public class CAudit extends BeanPath<Audit> {

    private static final long serialVersionUID = -2031835707;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final CAudit audit = new CAudit("audit");

    public final example.domain.shared.ddd.CAbstractValueObject _super = new example.domain.shared.ddd.CAbstractValueObject(this);

    public final DateTimePath<org.joda.time.DateTime> creationDate = createDateTime("creationDate", org.joda.time.DateTime.class);

    public final CAuditIdentity creator;

    public final DateTimePath<org.joda.time.DateTime> modificationDate = createDateTime("modificationDate", org.joda.time.DateTime.class);

    public final CAuditIdentity modifier;

    public CAudit(String variable) {
        this(Audit.class, forVariable(variable), INITS);
    }

    public CAudit(Path<? extends Audit> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public CAudit(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public CAudit(PathMetadata<?> metadata, PathInits inits) {
        this(Audit.class, metadata, inits);
    }

    public CAudit(Class<? extends Audit> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.creator = inits.isInitialized("creator") ? new CAuditIdentity(forProperty("creator")) : null;
        this.modifier = inits.isInitialized("modifier") ? new CAuditIdentity(forProperty("modifier")) : null;
    }

}

