package example.shared.bootstrap;

import org.springframework.core.Ordered;

public interface BootstrapOrder {

	int ACCOUNT = Ordered.HIGHEST_PRECEDENCE;

	int TODO = Ordered.HIGHEST_PRECEDENCE + 1;

	int EXAMPLE = Ordered.HIGHEST_PRECEDENCE + 1;

}
