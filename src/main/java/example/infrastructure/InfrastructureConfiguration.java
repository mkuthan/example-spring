package example.infrastructure;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import example.infrastructure.hibernate.HibernateConfiguration;
import example.infrastructure.spring.SpringConfiguration;

@Configuration
@Import({ HibernateConfiguration.class, SpringConfiguration.class })
public class InfrastructureConfiguration {
}
