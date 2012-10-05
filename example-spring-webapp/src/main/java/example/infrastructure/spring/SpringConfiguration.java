package example.infrastructure.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

@Configuration
@ComponentScan(basePackageClasses = { SpringConfiguration.class })
public class SpringConfiguration {

	@Bean
	public String passwordSalt() {
		return "example_salt";
	}

	@Bean
	public ShaPasswordEncoder shaPasswordEncoder() {
		return new ShaPasswordEncoder();
	}

}
