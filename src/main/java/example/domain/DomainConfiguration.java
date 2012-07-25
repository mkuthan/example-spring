package example.domain;

import org.springframework.beans.factory.aspectj.EnableSpringConfigured;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableSpringConfigured
@ComponentScan(basePackageClasses = { DomainConfiguration.class })
public class DomainConfiguration {
}
