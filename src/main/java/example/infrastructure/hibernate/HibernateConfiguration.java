package example.infrastructure.hibernate;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import example.domain.DomainConfiguration;

@Configuration
@ComponentScan(basePackageClasses = { HibernateConfiguration.class })
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
@EnableJpaRepositories(basePackageClasses = { DomainConfiguration.class })
public class HibernateConfiguration {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
	factoryBean.setDataSource(dataSource());
	factoryBean.setPackagesToScan(packagesToScan());

	factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
	factoryBean.setJpaPropertyMap(jpaPropertyMap());

	return factoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
	return new JpaTransactionManager(entityManagerFactory().getObject());
    }

    @Bean
    public DataSource dataSource() {
	return new EmbeddedDatabaseBuilder().setName("example").setType(EmbeddedDatabaseType.H2).build();
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
	return new HibernateJpaVendorAdapter();
    }

    @Bean
    public Map<String, Object> jpaPropertyMap() {
	Map<String, Object> jpaProperties = new HashMap<String, Object>();

	jpaProperties.put(Environment.HBM2DDL_AUTO, "create");

	return jpaProperties;
    }

    @Bean
    public String[] packagesToScan() {
	return new String[] { DomainConfiguration.class.getPackage().getName() };
    }

}
