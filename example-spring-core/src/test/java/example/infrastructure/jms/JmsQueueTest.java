package example.infrastructure.jms;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.base.Stopwatch;

import example.TestGroups;

@Test(groups = { TestGroups.INTEGRATION }, singleThreaded = true)
public class JmsQueueTest extends AbstractJmsTest {

	private static final JmsTestMessage ANY_MESSAGE = new JmsTestMessage();

	private static final int RECEIVE_TIMEOUT = 10000;

	@Autowired
	@Qualifier("testQueueJmsTemplate")
	private JmsTemplate jmsTemplate;

	@Autowired
	@Qualifier("testDlqQueueJmsTemplate")
	private JmsTemplate dlqJmsTemplate;

	@Autowired
	@Qualifier("testQueueListener")
	private JmsTestListener listener;

	@Value("${jms.expectedTotalRedeliveryTime}")
	private int expectedTotalRedeliveryTime;

	@BeforeMethod
	public void resetListener() {
		reset(listener);
	}

	@Test
	public void listenerShouldHandleMessage() {
		// when
		jmsTemplate.convertAndSend(ANY_MESSAGE);

		// then
		verify(listener, timeout(RECEIVE_TIMEOUT)).handleMessage(eq(ANY_MESSAGE));
	}

	@Test(dependsOnMethods = "listenerShouldHandleMessage")
	public void messageShouldBeRedelivered() {
		// given (first & second calls: throws exception, third call: handles message)
		doThrow(new RuntimeException("#1")).doThrow(new RuntimeException("#2")).doNothing().when(listener)
				.handleMessage(eq(ANY_MESSAGE));

		Stopwatch stopwatch = new Stopwatch().start();

		// when
		jmsTemplate.convertAndSend(ANY_MESSAGE);

		// then
		verify(listener, timeout(RECEIVE_TIMEOUT).times(3)).handleMessage(eq(ANY_MESSAGE));

		assertThat(stopwatch.stop().elapsed(TimeUnit.MILLISECONDS)).isGreaterThan(expectedTotalRedeliveryTime);
	}

	@Test(dependsOnMethods = "messageShouldBeRedelivered", timeOut = RECEIVE_TIMEOUT)
	public void messageShouldBeInDlqAfterRedeliveries() {
		// given (always throws exception)
		doThrow(new RuntimeException()).when(listener).handleMessage(eq(ANY_MESSAGE));

		Stopwatch stopwatch = new Stopwatch().start();

		// when
		jmsTemplate.convertAndSend(ANY_MESSAGE);

		JmsTestMessage testMessage = (JmsTestMessage) dlqJmsTemplate.receiveAndConvert();
		assertThat(testMessage).isEqualTo(ANY_MESSAGE);

		assertThat(stopwatch.stop().elapsed(TimeUnit.MILLISECONDS)).isGreaterThan(expectedTotalRedeliveryTime);
	}

}
