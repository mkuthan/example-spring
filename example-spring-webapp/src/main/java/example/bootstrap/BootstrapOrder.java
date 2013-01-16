package example.bootstrap;

import org.springframework.core.Ordered;

public interface BootstrapOrder {

	int EXAMPLE = Ordered.LOWEST_PRECEDENCE;

	int ACCOUNT = Ordered.HIGHEST_PRECEDENCE;

	int BLOG = ACCOUNT + 1;

}
