package example.infrastructure.jms;

public interface JmsTestListener {
	void handleMessage(JmsTestMessage message);
}