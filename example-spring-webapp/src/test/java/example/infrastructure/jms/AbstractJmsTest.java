package example.infrastructure.jms;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import example.TestGroups;

@Test(groups = { TestGroups.JMS })
@ContextConfiguration(locations = "classpath:/META-INF/spring/testContext-infrastructure-jms.xml")
public class AbstractJmsTest extends AbstractTestNGSpringContextTests {
}
