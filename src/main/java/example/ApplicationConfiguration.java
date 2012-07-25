package example;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import example.domain.DomainConfiguration;
import example.infrastructure.InfrastructureConfiguration;
import example.service.ServiceConfiguration;

@Configuration
@Import({ DomainConfiguration.class, ServiceConfiguration.class, InfrastructureConfiguration.class })
public class ApplicationConfiguration {

    @Bean
    public static PropertyPlaceholderConfigurer properties() {
	Resource[] resources = new ClassPathResource[] { new ClassPathResource("application.properties") };

	PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
	ppc.setLocations(resources);
	ppc.setIgnoreUnresolvablePlaceholders(true);

	return ppc;
    }

}
