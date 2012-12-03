package example.infrastructure.jms;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.base.Objects;

import example.TestGroups;

@Test(groups = { TestGroups.JMS })
@ContextConfiguration(locations = "classpath:/META-INF/spring/testContext-infrastructure-jms.xml")
public class JmsInfrastructureTest extends AbstractTestNGSpringContextTests {

	private static final int LISTENER_TIMEOUT = 3000;

	private static final TestMessage ANY_MESSAGE = new TestMessage();

	@Autowired
	@Qualifier("testJmsTemplate")
	JmsTemplate testJmsTemplate;

	@Autowired
	@Qualifier("testDlqJmsTemplate")
	JmsTemplate testDlqJmsTemplate;

	@Autowired
	TestListenerDelegate testListenerDelegate;

	@BeforeMethod
	public void resetListener() {
		reset(testListenerDelegate);
	}

	@Test
	public void listenerShouldHandleMessage() {
		// when
		testJmsTemplate.send(new ObjectMessageCreator(ANY_MESSAGE));

		// then
		verify(testListenerDelegate, timeout(LISTENER_TIMEOUT)).handleMessage(eq(ANY_MESSAGE));
	}

	@Test
	public void messageShouldBeRedelivered() {
		// given (first call: throws exception, second call: handles message)
		doThrow(new RuntimeException()).doNothing().when(testListenerDelegate).handleMessage(eq(ANY_MESSAGE));

		// when
		testJmsTemplate.send(new ObjectMessageCreator(ANY_MESSAGE));

		// then
		verify(testListenerDelegate, timeout(LISTENER_TIMEOUT).times(2)).handleMessage(eq(ANY_MESSAGE));
	}

	@Test
	public void messageShouldBeInDlqAfterRedeliveries() {
		// given (always throws exception)
		doThrow(new RuntimeException()).when(testListenerDelegate).handleMessage(eq(ANY_MESSAGE));

		// when
		testJmsTemplate.send(new ObjectMessageCreator(ANY_MESSAGE));

		TestMessage testMessage = (TestMessage) testDlqJmsTemplate.receiveAndConvert();
		assertThat(testMessage).isEqualTo(ANY_MESSAGE);
	}

	public static class TestListenerDelegate {
		public void handleMessage(TestMessage message) {
		}
	}

	public static class TestMessage implements Serializable {

		private static final long serialVersionUID = 1L;

		private UUID uuid = UUID.randomUUID();

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}

			if (getClass() != obj.getClass()) {
				return false;
			}

			TestMessage other = (TestMessage) obj;
			return Objects.equal(uuid, other.uuid);
		}

		@Override
		public int hashCode() {
			return Objects.hashCode(uuid);
		}

		@Override
		public String toString() {
			return Objects.toStringHelper(this).addValue(uuid).toString();
		}
	}

	public static class TestListener {
		public TestMessage handleMessage(TestMessage message) {
			return message;
		}
	}
}
