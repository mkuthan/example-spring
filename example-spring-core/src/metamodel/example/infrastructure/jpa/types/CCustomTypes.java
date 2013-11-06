package example.infrastructure.jpa.types;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * CCustomTypes is a Querydsl query type for CustomTypes
 */
@Generated("com.mysema.query.codegen.SupertypeSerializer")
public class CCustomTypes extends EntityPathBase<CustomTypes> {

    private static final long serialVersionUID = -488239591;

    public static final CCustomTypes customTypes = new CCustomTypes("customTypes");

    public CCustomTypes(String variable) {
        super(CustomTypes.class, forVariable(variable));
    }

    public CCustomTypes(Path<? extends CustomTypes> path) {
        super(path.getType(), path.getMetadata());
    }

    public CCustomTypes(PathMetadata<?> metadata) {
        super(CustomTypes.class, metadata);
    }

}

