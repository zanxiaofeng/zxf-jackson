package zxf.jackson.json.unicode;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UnicodeTests {

    //Test json string escape for \n \t \r　\u5F20(张)
    public static void main(String[] args) throws JsonProcessingException {
        String stringValue1 = "Davis\"\\昝-\u5F20-\b\t\f\r\n";
        String jsonStringValue1 = new ObjectMapper().writeValueAsString(stringValue1);
        System.out.println("1. " + stringValue1 + ".#");
        System.out.println("1. " + jsonStringValue1 + ".#");


        String jsonStringValue2 = "\"Davis\\\"\\\\昝-\\u5F20-\\b\\t\\f\\r\\n\"";
        String stringValue2 = new ObjectMapper().readValue(jsonStringValue2, String.class);
        System.out.println("2. " + jsonStringValue2 + ".#");
        System.out.println("2. " + stringValue2 + ".#");

        System.out.println("3. " + stringValue1.equals(stringValue2) + ", " + jsonStringValue1.equals(jsonStringValue2));
    }
}
