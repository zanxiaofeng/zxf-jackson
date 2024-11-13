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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class JacksonJsonTest {

    public static void main(String[] args) throws IOException, URISyntaxException {
        System.out.println(TimeZone.getDefault());
        ObjectMapper objectMapper1 = buildObjectMapper();

        String json = objectMapper1.writeValueAsString(createModel());
        System.out.println("1. " + json);

        MyAuthentication myAuthentication1 = (MyAuthentication) objectMapper1.readValue(json, Object.class);
        System.out.println("2. " + myAuthentication1.getName() + ", " + myAuthentication1.getMyUser().getCreateTime());

        // Please note "new File(JacksonTest.class.getClassLoader().getResource("example.json").toURI())" will not work when run this program by jar
        File file = new File(JacksonJsonTest.class.getClassLoader().getResource("example.json").toURI());
        MyAuthentication myAuthentication2 = (MyAuthentication) objectMapper1.readValue(file, Object.class);
        System.out.println("3. " + myAuthentication2 + ".#");

        Map<String, Object> model = createMapModel();
        ObjectMapper objectMapper2 = new ObjectMapper();
        objectMapper2.registerModule(new JavaTimeModule());
        MyAuthentication myAuthentication3 = objectMapper2.convertValue(model, MyAuthentication.class);
        System.out.println("4. " + objectMapper2.writeValueAsString(myAuthentication3) + ".#");
    }

    private static ObjectMapper buildObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        // register modules
        objectMapper.registerModules(SecurityJackson2Modules.getModules(JacksonJsonTest.class.getClassLoader()));
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(new ParameterNamesModule());

        // serialize fields instead of properties
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        // ignore unresolved fields (mostly 'principal')
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, false);
        objectMapper.configure(SerializationFeature.WRITE_DATES_WITH_CONTEXT_TIME_ZONE, true);

        objectMapper.setPropertyNamingStrategy(new MyNamingStrategy());

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
        myUser.put("createTime", ZonedDateTime.now().withZoneSameInstant(ZoneId.of("Australia/Sydney")));
        myUser.put("updateTime", LocalDateTime.now());

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
