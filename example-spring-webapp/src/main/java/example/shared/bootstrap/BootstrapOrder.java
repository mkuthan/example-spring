package example.shared.bootstrap;

import org.springframework.core.Ordered;

public interface BootstrapOrder {

	int TODO = Ordered.HIGHEST_PRECEDENCE + 1;

}
