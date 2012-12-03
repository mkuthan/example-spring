package example.infrastructure.jms;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import example.TestGroups;

@Test(groups = { TestGroups.JMS })
@ContextConfiguration(locations = "classpath:/META-INF/spring/testContext-infrastructure-jms.xml")
public class JmsQueuePerformanceTest extends AbstractTestNGSpringContextTests {

	private static final JmsTestMessage ANY_MESSAGE = new JmsTestMessage();

	private static final int NO_OF_PRODUCERS = 10;

	private static final int NO_OF_MESSAGES = 1000;

	@Autowired
	@Qualifier("testPerformanceQueueJmsTemplate")
	JmsTemplate jmsTemplate;

	@Autowired
	JmsTestListener listener;

	@Value("${jms.receiveTimeout}")
	int receiveTimeout;

	@BeforeClass
	public void resetListener() {
		reset(listener);
	}

	@Test(invocationCount = NO_OF_MESSAGES, threadPoolSize = NO_OF_PRODUCERS)
	public void shouldSendMessages() {
		jmsTemplate.send(new ObjectMessageCreator(ANY_MESSAGE));
	}

	@Test(dependsOnMethods = "shouldSendMessages")
	public void shouldHandleMessages() {
		verify(listener, timeout(receiveTimeout).times(NO_OF_MESSAGES)).handleMessage(eq(ANY_MESSAGE));
	}

}
