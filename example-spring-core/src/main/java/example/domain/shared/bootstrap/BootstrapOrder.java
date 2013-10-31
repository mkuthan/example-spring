package example.domain.shared.bootstrap;

import org.springframework.core.Ordered;

public interface BootstrapOrder {

	int IAM_PRECEDENCE = Ordered.HIGHEST_PRECEDENCE + 100;

	int IAM_GROUPS = IAM_PRECEDENCE;

	int IAM_USERS = IAM_PRECEDENCE + 1;

	int TODO_PRECEDENCE = Ordered.HIGHEST_PRECEDENCE + 200;

	int TODO_TODO = TODO_PRECEDENCE;

}
