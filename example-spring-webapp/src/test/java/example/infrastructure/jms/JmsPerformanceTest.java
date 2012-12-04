package example.infrastructure.jms;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.testng.annotations.Test;

public class JmsPerformanceTest extends AbstractJmsTest {

	private static final JmsTestMessage ANY_MESSAGE = new JmsTestMessage();

	private static final int NO_OF_PRODUCERS = 10;

	private static final int NO_OF_MESSAGES = 1000;

	private static final int RECEIVE_TIMEOUT = 500;

	@Autowired
	@Qualifier("testPerformanceJmsTemplate")
	private JmsTemplate jmsTemplate;

	@Autowired
	@Qualifier("testPerformanceListener")
	private JmsTestListener listener;

	@Test(invocationCount = NO_OF_MESSAGES, threadPoolSize = NO_OF_PRODUCERS, timeOut = 10000)
	public void shouldSendMessages() {
		jmsTemplate.send(new ObjectMessageCreator(ANY_MESSAGE));
	}

	@Test(dependsOnMethods = "shouldSendMessages")
	public void shouldHandleMessages() {
		verify(listener, timeout(RECEIVE_TIMEOUT).times(NO_OF_MESSAGES)).handleMessage(eq(ANY_MESSAGE));
	}

}
