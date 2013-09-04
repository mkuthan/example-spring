package example.infrastructure.jms;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@ContextConfiguration(locations = "classpath:/META-INF/spring/testContext-infrastructure-jms.xml")
@ActiveProfiles("test")
public class AbstractJmsTest extends AbstractTestNGSpringContextTests {
}
