package example.ddd;

import java.io.Serializable;

public interface Event<T> extends Serializable {

	T getPayload();

}
