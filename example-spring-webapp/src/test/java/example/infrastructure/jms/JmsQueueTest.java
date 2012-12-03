package example.infrastructure.jms;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JmsQueueTest extends AbstractJmsTest {

	private static final JmsTestMessage ANY_MESSAGE = new JmsTestMessage();

	@Autowired
	@Qualifier("testQueueJmsTemplate")
	JmsTemplate jmsTemplate;

	@Autowired
	@Qualifier("testDlqQueueJmsTemplate")
	JmsTemplate dlqJmsTemplate;

	@Autowired
	@Qualifier("testQueueListener")
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
		verify(listener, timeout(receiveTimeout)).handleMessage(eq(ANY_MESSAGE));
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

	@Test
	public void messageShouldBeInDlqAfterRedeliveries() {
		// given (always throws exception)
		doThrow(new RuntimeException()).when(listener).handleMessage(eq(ANY_MESSAGE));

		// when
		jmsTemplate.send(new ObjectMessageCreator(ANY_MESSAGE));

		JmsTestMessage testMessage = (JmsTestMessage) dlqJmsTemplate.receiveAndConvert();
		assertThat(testMessage).isEqualTo(ANY_MESSAGE);
	}

}
