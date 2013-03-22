package example.infrastructure.cache;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import example.TestGroups;

@ContextConfiguration(locations = "classpath:/META-INF/spring/testContext-cache.xml")
@Test(groups = { TestGroups.INTEGRATION })
@ActiveProfiles("test")
public class CacheTest extends AbstractTestNGSpringContextTests {

	private static final String ANY_NAME = "any name";

	@Autowired
	private TestFascadeService testFascadeService;

	@Autowired
	private TestCachedService testCachedService;

	@BeforeMethod
	public void initializeMocks() {
		reset(testCachedService);

		when(testCachedService.sayHello(anyString())).thenReturn("any message");
	}

	@Test
	public void firstExecutionShouldCallCachedService() {
		testFascadeService.sayHello(ANY_NAME);

		verify(testCachedService).sayHello(eq(ANY_NAME));
	}

	@Test(dependsOnMethods = "firstExecutionShouldCallCachedService")
	public void secondExecutionShouldUseCache() {
		testFascadeService.sayHello(ANY_NAME);

		verifyZeroInteractions(testCachedService);
	}

	@Component
	public static class TestFascadeService {

		@Autowired
		private TestCachedService cachedService;

		@Cacheable("example")
		public String sayHello(String name) {
			return cachedService.sayHello(name);
		}
	}

	public static interface TestCachedService {
		public String sayHello(String name);
	}

}
