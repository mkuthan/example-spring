package example.infrastructure.jms;

import static com.google.common.base.Preconditions.*;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.MessageCreator;

public class ObjectMessageCreator implements MessageCreator {

	private Serializable object;

	public ObjectMessageCreator(Serializable object) {
		this.object = checkNotNull(object);
	}

	@Override
	public Message createMessage(Session session) throws JMSException {
		return session.createObjectMessage(object);
	}

}
