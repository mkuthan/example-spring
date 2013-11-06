package example.domain.shared.ddd;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * CAbstractValueObject is a Querydsl query type for AbstractValueObject
 */
@Generated("com.mysema.query.codegen.EmbeddableSerializer")
public class CAbstractValueObject extends BeanPath<AbstractValueObject> {

    private static final long serialVersionUID = 941902785;

    public static final CAbstractValueObject abstractValueObject = new CAbstractValueObject("abstractValueObject");

    public CAbstractValueObject(String variable) {
        super(AbstractValueObject.class, forVariable(variable));
    }

    public CAbstractValueObject(Path<? extends AbstractValueObject> path) {
        super(path.getType(), path.getMetadata());
    }

    public CAbstractValueObject(PathMetadata<?> metadata) {
        super(AbstractValueObject.class, metadata);
    }

}

