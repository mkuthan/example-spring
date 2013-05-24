package example.infrastructure.events;

import static org.fest.assertions.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import example.TestGroups;
import example.ddd.AbstractEvent;
import example.ddd.EventListener;

@ContextConfiguration(locations = "classpath:/META-INF/spring/testContext-events.xml")
@Test(groups = { TestGroups.INTEGRATION }, singleThreaded = true)
@ActiveProfiles("test")
public class EventsTests extends AbstractTestNGSpringContextTests {

	@Autowired
	private DefaultEventPublisher defaultEventPublisher;

	@Autowired
	private TestSyncEventListener testSyncEventListener;

	@Autowired
	private TestAsyncEventListener testAsyncEventListener;

	@Test
	public void shouldReceiveSyncEvent() {
		// given
		TestEvent event = new TestEvent("any payload");

		// when
		defaultEventPublisher.publish(event);

		// then
		assertThat(testSyncEventListener.getEvent()).isEqualTo(event);
		assertThat(testSyncEventListener.getThread()).isEqualTo(Thread.currentThread());
	}

	@Test
	public void shouldReceiveAsyncEvent() throws InterruptedException {
		// given
		TestEvent event = new TestEvent("any payload");

		// when
		defaultEventPublisher.publish(event);

		synchronized (testAsyncEventListener) {
			testAsyncEventListener.wait(500);
		}

		// then
		assertThat(testAsyncEventListener.getEvent()).isEqualTo(event);
		assertThat(testAsyncEventListener.getThread()).isNotEqualTo(Thread.currentThread());
	}

	@Component
	public static class TestSyncEventListener {

		private TestEvent event;
		private Thread thread;

		@EventListener
		public void sync(TestEvent event) {
			this.event = event;
			this.thread = Thread.currentThread();
		}

		public TestEvent getEvent() {
			return event;
		}

		public Thread getThread() {
			return thread;
		}

	}

	@Component
	public static class TestAsyncEventListener {

		private volatile TestEvent event;
		private volatile Thread thread;

		@EventListener(async = true)
		public synchronized void async(TestEvent event) {
			this.event = event;
			this.thread = Thread.currentThread();

			notifyAll();
		}

		public TestEvent getEvent() {
			return event;
		}

		public Thread getThread() {
			return thread;
		}

	}

	public static class TestEvent extends AbstractEvent<String> {

		private static final long serialVersionUID = 1L;

		public TestEvent(String payload) {
			super(payload);
		}

	}
}
