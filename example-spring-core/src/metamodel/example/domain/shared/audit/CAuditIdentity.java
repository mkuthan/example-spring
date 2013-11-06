package example.domain.shared.audit;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * CAuditIdentity is a Querydsl query type for AuditIdentity
 */
@Generated("com.mysema.query.codegen.EmbeddableSerializer")
public class CAuditIdentity extends BeanPath<AuditIdentity> {

    private static final long serialVersionUID = -1831082589;

    public static final CAuditIdentity auditIdentity = new CAuditIdentity("auditIdentity");

    public final example.domain.shared.ddd.CAbstractValueObject _super = new example.domain.shared.ddd.CAbstractValueObject(this);

    public final StringPath details = createString("details");

    public final StringPath identity = createString("identity");

    public CAuditIdentity(String variable) {
        super(AuditIdentity.class, forVariable(variable));
    }

    public CAuditIdentity(Path<? extends AuditIdentity> path) {
        super(path.getType(), path.getMetadata());
    }

    public CAuditIdentity(PathMetadata<?> metadata) {
        super(AuditIdentity.class, metadata);
    }

}

