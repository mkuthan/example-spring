package example.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = { ServiceConfiguration.class })
public class ServiceConfiguration {
}
