package example.bootstrap;

import org.springframework.core.Ordered;

public interface BootstrapOrder {

	int ACCOUNT = Ordered.LOWEST_PRECEDENCE;

	int EXAMPLE = Ordered.LOWEST_PRECEDENCE;

}
