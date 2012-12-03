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

	@Autowired
	@Qualifier("testPerformanceJmsTemplate")
	JmsTemplate jmsTemplate;

	@Autowired
	@Qualifier("testPerformanceListener")
	JmsTestListener listener;

	@Test(invocationCount = NO_OF_MESSAGES, threadPoolSize = NO_OF_PRODUCERS)
	public void shouldSendMessages() {
		jmsTemplate.send(new ObjectMessageCreator(ANY_MESSAGE));
	}

	@Test(dependsOnMethods = "shouldSendMessages")
	public void shouldHandleMessages() {
		verify(listener, timeout(receiveTimeout).times(NO_OF_MESSAGES)).handleMessage(eq(ANY_MESSAGE));
	}

}
