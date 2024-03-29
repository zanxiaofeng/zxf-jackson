package zxf.jackson.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import zxf.jackson.json.model.MyAuthentication;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

public class JacksonJsonTest {

    public static void main(String[] args) throws IOException, URISyntaxException {
        ObjectMapper objectMapper = buildObjectMapper();

        String json = objectMapper.writeValueAsString(createModel());
        System.out.println(json);

        MyAuthentication myAuthentication1 = (MyAuthentication) objectMapper.readValue(json, Object.class);
        System.out.println(myAuthentication1.getName());

        //Test json string escape for \n \t \r　\u5f20(张)
        String value = (String) objectMapper.readValue("\"\\\\davis\\\"昝 \\u5f20\\b\\t\\f\\r\\n\"", String.class);
        System.out.println(value + ".#");

        // Please note "new File(JacksonTest.class.getClassLoader().getResource("example.json").toURI())" will not work when run this program by jar
        File file = new File(JacksonJsonTest.class.getClassLoader().getResource("example.json").toURI());
        MyAuthentication myAuthentication2 = (MyAuthentication) objectMapper.readValue(file, Object.class);
        System.out.println(myAuthentication2.getName() + ".#");

        Map<String, Object> model = createMapModel();
        MyAuthentication myAuthentication3 = objectMapper.convertValue(model, MyAuthentication.class);
        System.out.println(objectMapper.writeValueAsString(myAuthentication3));
    }

    private static ObjectMapper buildObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        // serialize fields instead of properties
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        // ignore unresolved fields (mostly 'principal')
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        objectMapper.setPropertyNamingStrategy(new MyNamingStrategy());

        // register modules
        objectMapper.registerModules(SecurityJackson2Modules.getModules(JacksonJsonTest.class.getClassLoader()));
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(new ParameterNamesModule());

        // add mixin
        objectMapper.addMixIn(HashMap.class, HashMapMixin.class);

        // serialize json with @class
        objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);

        return objectMapper;
    }

    private static MyAuthentication createModel() {
        return new MyAuthentication(new MyAuthentication.MyUser("davis 昝"));
    }

    private static Map<String, Object> createMapModel() {
        Map<String, Object> myUser = new HashMap<>();
        myUser.put("name", "ben");
        myUser.put("age", 20);
        myUser.put("createTime", ZonedDateTime.now());

        Map<String, Object> model = new HashMap<>();
        model.put("myUser", myUser);
        return model;
    }


    private static class MyNamingStrategy extends PropertyNamingStrategy.PropertyNamingStrategyBase {

        @Override
        public String translate(String propertyName) {

            switch (propertyName) {
                case "name":
                    return "_name";
                case "_name":
                    return "name";
                default:
                    return propertyName;
            }
        }

    }

    private static class HashMapMixin {

        // Nothing special

    }
}
