import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonParser {
    private final ObjectMapper objectMapper;

    public JsonParser() {
        this.objectMapper = new ObjectMapper();
    }

    public List<Map<String, Object>> parse(String json) throws IOException {
        Map<String, Object> parsedJson = objectMapper.readValue(json, new TypeReference<>() {
        });
        Object results = parsedJson.get("results");
        return objectMapper.convertValue(results, new TypeReference<>() {
        });
    }
}
