package zxf.jackson.json;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

public class JacksonJsonUnwrappedTest {

    public static void main(String[] args) throws JsonProcessingException {
        MyParameters myParameters = new MyParameters();
        myParameters.id = 1;
        myParameters.name = "davis";
        myParameters.values = new HashMap<>();
        myParameters.values.put("P1", "1");
        myParameters.values.put("P2", "abc");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(myParameters);
        System.out.println("json: " + json);

        Object object = objectMapper.readValue(json, MyParameters.class);
        System.out.println("object: " + object);
    }

    @ToString
    public static class MyParameters {
        public int id;

        public String name;

        @JsonUnwrapped(prefix = "aaaa")
        public Map<String, String> values;
    }
}
