package example.domain.iam.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * CUserIdentifier is a Querydsl query type for UserIdentifier
 */
@Generated("com.mysema.query.codegen.EmbeddableSerializer")
public class CUserIdentifier extends BeanPath<UserIdentifier> {

    private static final long serialVersionUID = 1800948664;

    public static final CUserIdentifier userIdentifier = new CUserIdentifier("userIdentifier");

    public final example.domain.shared.ddd.CAbstractValueObject _super = new example.domain.shared.ddd.CAbstractValueObject(this);

    public final StringPath identifier = createString("identifier");

    public CUserIdentifier(String variable) {
        super(UserIdentifier.class, forVariable(variable));
    }

    public CUserIdentifier(Path<? extends UserIdentifier> path) {
        super(path.getType(), path.getMetadata());
    }

    public CUserIdentifier(PathMetadata<?> metadata) {
        super(UserIdentifier.class, metadata);
    }

}

