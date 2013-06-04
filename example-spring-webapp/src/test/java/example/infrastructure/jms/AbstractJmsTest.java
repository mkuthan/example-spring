package example.infrastructure.jms;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import example.TestGroups;

@ContextConfiguration(locations = "classpath:/META-INF/spring/testContext-jms.xml")
@Test(groups = { TestGroups.INTEGRATION }, singleThreaded = true)
@ActiveProfiles("test")
public class AbstractJmsTest extends AbstractTestNGSpringContextTests {
}
