package example.domain.shared.bootstrap;

import org.springframework.core.Ordered;

public interface BootstrapOrder {

	int IAM_ROLES = Ordered.HIGHEST_PRECEDENCE;

	int TODO = Ordered.HIGHEST_PRECEDENCE + 1;

}
