package example.infrastructure.jpa.repositories;

import org.springframework.stereotype.Component;

import example.domain.todo.domain.Todo;
import example.domain.todo.domain.TodoRepository;

@Component
public class JpaTodoRepository extends AbstractJpaRepository<Todo, Long> implements TodoRepository {
}
