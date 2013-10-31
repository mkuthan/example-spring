package example.infrastructure.date;

import static org.fest.assertions.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import example.TestGroups;

@ContextConfiguration(locations = "classpath:/META-INF/spring/testContext-infrastructure-date.xml")
@Test(groups = TestGroups.INTEGRATION, singleThreaded = true)
@ActiveProfiles("test")
public class DefaultDateTimeProviderTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private DefaultDateTimeProvider defaultDateTimeProvider;

	@Test
	public void shouldNotBeNull() {
		assertThat(defaultDateTimeProvider.currentDateTime()).isNotNull();
	}
	
}
