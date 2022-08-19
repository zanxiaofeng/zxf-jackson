package zxf.jackson;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import zxf.jackson.model.MyAuthentication;

import java.util.HashMap;

public class JacksonTest {

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = buildObjectMapper();
        String json = objectMapper.writeValueAsString(createModel());
        System.out.println(json);
        MyAuthentication myAuthentication = (MyAuthentication) objectMapper.readValue(json, Object.class);
        System.out.println(myAuthentication.getName());
    }

    private static ObjectMapper buildObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        // serialize fields instead of properties
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        // ignore unresolved fields (mostly 'principal')
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        objectMapper.setPropertyNamingStrategy(new MyNamingStrategy());

        // register modules
        objectMapper.registerModules(SecurityJackson2Modules.getModules(JacksonTest.class.getClassLoader()));
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(new ParameterNamesModule());

        // add mixin
        objectMapper.addMixIn(HashMap.class, HashMapMixin.class);

        // serialize json with @class
        objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);

        return objectMapper;
    }

    private static MyAuthentication createModel(){
        return new MyAuthentication(new MyAuthentication.MyUser("davis"));
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
