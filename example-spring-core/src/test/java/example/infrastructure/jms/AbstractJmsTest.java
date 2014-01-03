package example.infrastructure.jms;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import example.TestGroups;

@ContextConfiguration(locations = "classpath:/META-INF/spring/testContext-infrastructure-jms.xml")
@ActiveProfiles("test")
@Test(groups = { TestGroups.INTEGRATION }, singleThreaded = true)
public class AbstractJmsTest extends AbstractTestNGSpringContextTests {
}
