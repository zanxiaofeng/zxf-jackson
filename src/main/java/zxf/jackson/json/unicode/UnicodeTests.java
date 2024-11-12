package zxf.jackson.json.unicode;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UnicodeTests {
    public static void main(String[] args) throws JsonProcessingException {
        String stringValue1 = "Davis\"\\昝-\u5F20-\b\t\f\r\n";
        System.out.println("1. " + stringValue1 + ".#");

        //Test json string escape for \n \t \r　\u5F20(张)
        String stringValue2 = new ObjectMapper().readValue("\"Davis\\\"\\\\昝-\\u5F20-\\b\\t\\f\\r\\n\"", String.class);
        System.out.println("2. " + stringValue2 + ".#");

        System.out.println("3. " + stringValue1.equals(stringValue2));
    }
}
