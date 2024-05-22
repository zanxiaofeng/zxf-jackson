package zxf.jackson.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import zxf.jackson.json.model.JsonNodeBean;

public class TestJsonNode {
    public static void main(String[] args) throws JsonProcessingException {
        String json = "{\"name\": \"davis\", \"json\":{\"a\":1,\"b\":2}}";

        JsonNode jsonNode = new ObjectMapper().readTree(json);

        JsonNodeBean jsonNodeBean = new ObjectMapper().readValue(json, JsonNodeBean.class);


        System.out.println(new ObjectMapper().writeValueAsString(jsonNodeBean));
    }
}
