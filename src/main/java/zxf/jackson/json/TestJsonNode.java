package zxf.jackson.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import zxf.jackson.json.model.JsonNodeBean;

import java.lang.reflect.Type;
import java.util.Map;

public class TestJsonNode {
    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final static TypeReference<Map<String, Object>> MAP_TYPE = new TypeReference<Map<String, Object>>() {
        @Override
        public Type getType() {
            return super.getType();
        }
    };

    public static void main(String[] args) throws JsonProcessingException {
        String json = "{\"name\": \"davis\", \"json\":{\"a\":1,\"b\":true,\"c\":\"str\",\"d\":1.01,\"e\":null,\"f\":[\"a\",\"b\",\"c\"]}}";
        treeOps(json);
        beanOps(json);
        convert(json);
    }

    private static void treeOps(String json) throws JsonProcessingException {
        JsonNode jsonTree = OBJECT_MAPPER.readTree(json);
        System.out.println("Tree Ops: " + jsonTree.toString());

        JsonNodeBean jsonNodeBean = OBJECT_MAPPER.treeToValue(jsonTree, JsonNodeBean.class);
        System.out.println("Tree Ops: " + OBJECT_MAPPER.writeValueAsString(jsonNodeBean));
        System.out.println("Tree Ops: obj.json=" + jsonNodeBean.getJson().getClass());
        System.out.println("Tree Ops: obj.json.a=" + jsonNodeBean.getJson().get("a").getClass());
        System.out.println("Tree Ops: obj.json.b=" + jsonNodeBean.getJson().get("b").getClass());
        System.out.println("Tree Ops: obj.json.c=" + jsonNodeBean.getJson().get("c").getClass());
        System.out.println("Tree Ops: obj.json.d=" + jsonNodeBean.getJson().get("d").getClass());
        System.out.println("Tree Ops: obj.json.e=" + jsonNodeBean.getJson().get("e").getClass());
        System.out.println("Tree Ops: obj.json.f=" + jsonNodeBean.getJson().get("f").getClass());

        Map<String, Object> jsonMap = OBJECT_MAPPER.treeToValue(jsonTree, MAP_TYPE);
        System.out.println("Tree Ops: " + jsonMap);
        System.out.println("Tree Ops: map=" + jsonMap.getClass());
        System.out.println("Tree Ops: map.name=" + jsonMap.get("name").getClass());
        System.out.println("Tree Ops: map.json=" + jsonMap.get("json").getClass());
        System.out.println("Tree Ops: map.json.a=" + ((Map) jsonMap.get("json")).get("a").getClass());
        System.out.println("Tree Ops: map.json.b=" + ((Map) jsonMap.get("json")).get("b").getClass());
        System.out.println("Tree Ops: map.json.c=" + ((Map) jsonMap.get("json")).get("c").getClass());
        System.out.println("Tree Ops: map.json.d=" + ((Map) jsonMap.get("json")).get("d").getClass());
        System.out.println("Tree Ops: map.json.f=" + ((Map) jsonMap.get("json")).get("f").getClass());
    }

    private static void beanOps(String json) throws JsonProcessingException {
        JsonNodeBean jsonNodeBean = OBJECT_MAPPER.readValue(json, JsonNodeBean.class);
        System.out.println("Bean Ops: " + OBJECT_MAPPER.writeValueAsString(jsonNodeBean));

        JsonNode jsonTree = OBJECT_MAPPER.valueToTree(jsonNodeBean);
        System.out.println("Bean Ops: " + jsonTree.toString());
    }

    private static void convert(String json) throws JsonProcessingException {
        JsonNode jsonTree = OBJECT_MAPPER.readTree(json);
        JsonNodeBean jsonNodeBean = OBJECT_MAPPER.treeToValue(jsonTree, JsonNodeBean.class);
        Map<String, Object> jsonMap = OBJECT_MAPPER.treeToValue(jsonTree, MAP_TYPE);

        System.out.println("Tree -> Bean: " + OBJECT_MAPPER.convertValue(jsonTree, JsonNodeBean.class));
        System.out.println("Tree -> Map : " + OBJECT_MAPPER.convertValue(jsonTree, MAP_TYPE));

        System.out.println("Bean -> Tree: " + OBJECT_MAPPER.convertValue(jsonNodeBean, JsonNode.class));
        System.out.println("Bean -> Map : " + OBJECT_MAPPER.convertValue(jsonNodeBean, MAP_TYPE));

        System.out.println("Map -> Tree : " + OBJECT_MAPPER.convertValue(jsonMap, JsonNode.class));
        System.out.println("Map -> Bean : " + OBJECT_MAPPER.convertValue(jsonMap, JsonNodeBean.class));
    }
}
