package zxf.jackson.json.model;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonNodeBean {
    private String name;
    private JsonNode json;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JsonNode getJson() {
        return json;
    }

    public void setJson(JsonNode json) {
        this.json = json;
    }
}
