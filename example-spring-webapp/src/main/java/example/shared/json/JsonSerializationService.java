package example.shared.json;

public interface JsonSerializationService {

	<T> String toJson(T object) throws JsonSerializationException;

	<T> T fromJson(String json, String type) throws JsonSerializationException;

	<T> T fromJson(String json, Class<T> type) throws JsonSerializationException;

}
