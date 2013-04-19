package example.bootstrap;

import org.springframework.core.Ordered;

public interface BootstrapOrder {

	int ACCOUNT = Ordered.HIGHEST_PRECEDENCE;

	int EXAMPLE = Ordered.HIGHEST_PRECEDENCE + 1;

}
