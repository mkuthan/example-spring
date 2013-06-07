package example.infrastructure.jackson;

import static com.google.common.base.Preconditions.checkNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

import example.shared.json.JsonSerializationException;
import example.shared.json.JsonSerializationService;

@Component
public class Jackson2JsonSerializationService implements JsonSerializationService {

	private ObjectMapper objectMapper;

	@Autowired
	public Jackson2JsonSerializationService(ObjectMapper objectMapper) {
		this.objectMapper = checkNotNull(objectMapper);
	}

	@Override
	public <T> String toJson(T object) {
		try {
			ObjectWriter objectWriter = objectMapper.writer();
			return objectWriter.writeValueAsString(object);
		} catch (Exception e) {
			throw new JsonSerializationException(e.getMessage(), e);
		}
	}

	@Override
	public <T> T fromJson(String json, Class<T> type) {
		try {
			ObjectReader objectReader = objectMapper.reader(type);
			return objectReader.<T> readValue(json);
		} catch (Exception e) {
			throw new JsonSerializationException(e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T fromJson(String json, String type) {
		try {
			Class<T> typeClass = (Class<T>) Class.forName(type);
			return fromJson(json, typeClass);
		} catch (ClassNotFoundException e) {
			throw new JsonSerializationException(e.getMessage(), e);
		}
	}

}
