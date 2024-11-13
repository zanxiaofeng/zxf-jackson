package zxf.jackson.json;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import zxf.jackson.json.model.InjectionBean;


public class JsonInjectTests {
    public static void main(String[] args) throws JsonProcessingException {
        System.out.println("WithInject:");
        String injectString = jsonWithInject("\\u0027 and 1=1--");
        System.out.println(injectString);
        InjectionBean injectBean = new ObjectMapper().readValue(injectString, InjectionBean.class);
        System.out.println(injectBean);
        System.out.println(generateSql(injectBean));

        System.out.println("\nWithoutInject:");
        String noInjectString = jsonWithoutInject("\\u0027 and 1=2--");
        System.out.println(noInjectString);
        InjectionBean noInjectBean = new ObjectMapper().readValue(noInjectString, InjectionBean.class);
        System.out.println(noInjectBean);
        System.out.println(generateSql(noInjectBean));
    }

    private static String jsonWithInject(String content) {
        return String.format("{\"id\": \"abc\", \"pass\":\"%s\"}", content);
    }

    private static String jsonWithoutInject(String content) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        root.put("id", "abc");
        root.put("pass", content);
        return root.toString();
    }

    private static String generateSql(InjectionBean injectionBean) {
        return String.format("SELECT * FROM abc WHERE id='%s' and password='%s'", injectionBean.getId(), injectionBean.getPass());
    }
}
