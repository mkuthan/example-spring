package example.infrastructure.jms;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.testng.annotations.Test;

import example.TestGroups;

@Test(groups = { TestGroups.PERFORMANCE })
public class JmsPerformanceTest extends AbstractJmsTest {

	private static final JmsTestMessage ANY_MESSAGE = new JmsTestMessage();

	private static final int NO_OF_PRODUCERS = 10;

	private static final int NO_OF_MESSAGES = 5000;

	private static final int RECEIVE_TIMEOUT = 500;

	@Autowired
	@Qualifier("testPerformanceJmsTemplate")
	private JmsTemplate jmsTemplate;

	@Autowired
	@Qualifier("testPerformanceListener")
	private JmsTestListener listener;

	@Test(invocationCount = NO_OF_MESSAGES, threadPoolSize = NO_OF_PRODUCERS)
	public void shouldSendMessages() {
		jmsTemplate.convertAndSend(ANY_MESSAGE);
	}

	@Test(dependsOnMethods = "shouldSendMessages")
	public void shouldHandleMessages() {
		verify(listener, timeout(RECEIVE_TIMEOUT).times(NO_OF_MESSAGES)).handleMessage(eq(ANY_MESSAGE));
	}

}
