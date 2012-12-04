package example.infrastructure.jms;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.base.Stopwatch;

public class JmsTopicTest extends AbstractJmsTest {

	private static final JmsTestMessage ANY_MESSAGE = new JmsTestMessage();

	private static final int RECEIVE_TIMEOUT = 10000;

	private static final int NO_OF_CONSUMERS = 2;

	@Autowired
	@Qualifier("testTopicJmsTemplate")
	private JmsTemplate jmsTemplate;

	@Autowired
	@Qualifier("testDlqTopicJmsTemplate")
	private JmsTemplate dlqJmsTemplate;

	@Autowired
	@Qualifier("testTopicListener1")
	private JmsTestListener listener1;

	@Autowired
	@Qualifier("testTopicListener1")
	private JmsTestListener listener2;

	@Value("${jms.expectedTotalRedeliveryTime}")
	private int expectedTotalRedeliveryTime;

	@BeforeMethod
	public void resetListener() {
		reset(listener1, listener2);
	}

	@Test
	public void listenersShouldHandleMessage() {
		// when
		jmsTemplate.send(new ObjectMessageCreator(ANY_MESSAGE));

		// then
		verify(listener1, timeout(RECEIVE_TIMEOUT).times(1)).handleMessage(eq(ANY_MESSAGE));
		verify(listener2, timeout(RECEIVE_TIMEOUT).times(1)).handleMessage(eq(ANY_MESSAGE));
	}

	@Test(dependsOnMethods = "listenersShouldHandleMessage")
	public void messageShouldBeRedelivered() {
		// given (first & second calls: throws exception, third call: handles message)
		doThrow(new RuntimeException("#1")).doThrow(new RuntimeException("#2")).doNothing().when(listener1)
				.handleMessage(eq(ANY_MESSAGE));
		doThrow(new RuntimeException("#1")).doThrow(new RuntimeException("#2")).doNothing().when(listener2)
				.handleMessage(eq(ANY_MESSAGE));

		Stopwatch stopwatch = new Stopwatch().start();

		// when
		jmsTemplate.send(new ObjectMessageCreator(ANY_MESSAGE));

		// then
		verify(listener1, timeout(RECEIVE_TIMEOUT).times(3)).handleMessage(eq(ANY_MESSAGE));
		verify(listener2, timeout(RECEIVE_TIMEOUT).times(3)).handleMessage(eq(ANY_MESSAGE));

		assertThat(stopwatch.stop().elapsedTime(TimeUnit.MILLISECONDS)).isGreaterThan(expectedTotalRedeliveryTime);
	}

	// TODO: fix test
	@Test(dependsOnMethods = "messageShouldBeRedelivered", timeOut = RECEIVE_TIMEOUT, enabled = false)
	public void messageShouldBeInDlqAfterRedeliveries() {
		// given (always throws exception)
		doThrow(new RuntimeException()).when(listener1).handleMessage(eq(ANY_MESSAGE));
		doThrow(new RuntimeException()).when(listener2).handleMessage(eq(ANY_MESSAGE));

		Stopwatch stopwatch = new Stopwatch().start();

		// when
		jmsTemplate.send(new ObjectMessageCreator(ANY_MESSAGE));

		JmsTestMessage testMessage1 = (JmsTestMessage) dlqJmsTemplate.receiveAndConvert();
		assertThat(testMessage1).isEqualTo(ANY_MESSAGE);

		JmsTestMessage testMessage2 = (JmsTestMessage) dlqJmsTemplate.receiveAndConvert();
		assertThat(testMessage2).isEqualTo(ANY_MESSAGE);

		assertThat(stopwatch.stop().elapsedTime(TimeUnit.MILLISECONDS)).isGreaterThan(expectedTotalRedeliveryTime);
	}

}
