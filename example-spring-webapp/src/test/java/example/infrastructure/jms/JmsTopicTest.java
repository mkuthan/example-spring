package example.infrastructure.jms;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JmsTopicTest extends AbstractJmsTest {

	private static final JmsTestMessage ANY_MESSAGE = new JmsTestMessage();

	private static final int NO_OF_CONSUMERS = 2;

	@Autowired
	@Qualifier("testTopicJmsTemplate")
	JmsTemplate jmsTemplate;

	@Autowired
	@Qualifier("testTopicListener")
	JmsTestListener listener;

	@BeforeMethod
	public void resetListener() {
		reset(listener);
	}

	@Test
	public void listenerShouldHandleMessage() {
		// when
		jmsTemplate.send(new ObjectMessageCreator(ANY_MESSAGE));

		// then
		verify(listener, timeout(receiveTimeout).times(NO_OF_CONSUMERS)).handleMessage(eq(ANY_MESSAGE));
	}

	@Test
	public void messageShouldBeRedelivered() {
		// given (first & second calls: throws exception, third call: handles message)
		doThrow(new RuntimeException("#1")).doThrow(new RuntimeException("#2")).doNothing().when(listener)
				.handleMessage(eq(ANY_MESSAGE));

		// when
		jmsTemplate.send(new ObjectMessageCreator(ANY_MESSAGE));

		// then
		verify(listener, timeout(receiveTimeout).times(3)).handleMessage(eq(ANY_MESSAGE));
	}

}
