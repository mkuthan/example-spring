package example.domain.shared.ddd;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public @interface DomainService {
}
