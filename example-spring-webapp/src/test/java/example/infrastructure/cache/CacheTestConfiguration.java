package example.infrastructure.cache;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import example.infrastructure.cache.CacheTest.TestCachedService;

@Configuration
public class CacheTestConfiguration {

	@Bean
	public TestCachedService testCachedService() {
		return Mockito.mock(TestCachedService.class);
	}

}
