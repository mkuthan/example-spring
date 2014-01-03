package example.infrastructure.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@ComponentScan
@EnableCaching
public class CacheConfig {

	@Bean
	public CacheManager cacheManager() {
		EhCacheCacheManager cacheManager = new EhCacheCacheManager();

		cacheManager.setCacheManager(ehCacheManagerFactoryBean().getObject());

		return cacheManager;
	}

	@Bean
	public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
		EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();

		ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("/ehcache.xml"));
		ehCacheManagerFactoryBean.setCacheManagerName("example");
		ehCacheManagerFactoryBean.setShared(true);

		return ehCacheManagerFactoryBean;
	}

}
