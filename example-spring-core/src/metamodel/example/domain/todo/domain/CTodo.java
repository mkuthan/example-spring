package example.domain.todo.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * CTodo is a Querydsl query type for Todo
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class CTodo extends EntityPathBase<Todo> {

    private static final long serialVersionUID = 58708238;

    public static final CTodo todo = new CTodo("todo");

    public final example.domain.shared.ddd.CAbstractAggregateEntity _super = new example.domain.shared.ddd.CAbstractAggregateEntity(this);

    //inherited
    public final NumberPath<Long> entityId = _super.entityId;

    //inherited
    public final NumberPath<Integer> entityVersion = _super.entityVersion;

    public final EnumPath<Todo.Status> status = createEnum("status", Todo.Status.class);

    public final StringPath title = createString("title");

    public CTodo(String variable) {
        super(Todo.class, forVariable(variable));
    }

    public CTodo(Path<? extends Todo> path) {
        super(path.getType(), path.getMetadata());
    }

    public CTodo(PathMetadata<?> metadata) {
        super(Todo.class, metadata);
    }

}

