package zxf.jackson.yaml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import zxf.jackson.json.JacksonJsonTest;
import zxf.jackson.yaml.model.Customer;
import zxf.jackson.yaml.model.Order;
import zxf.jackson.yaml.model.OrderLine;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;

import static com.fasterxml.jackson.dataformat.yaml.YAMLGenerator.Feature.WRITE_DOC_START_MARKER;

public class YamlTests {
    public static void main(String[] args) throws IOException, URISyntaxException {
        testReadYaml();
        testWriteYaml();
    }

    private static void testReadYaml() throws IOException, URISyntaxException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.registerModule(new JavaTimeModule());

        File file1 = new File(JacksonJsonTest.class.getResource("/yaml/example-1.yml").toURI());
        Order order11 = mapper.readValue(file1, Order.class);
        System.out.println("Read-1-1: " + order11);

        Map order12 = mapper.readValue(file1, Map.class);
        System.out.println("Read-1-2: " + order12);
    }

    private static void testWriteYaml() throws IOException, URISyntaxException {
        YAMLFactory yamlFactory = new YAMLFactory();
        yamlFactory.disable(WRITE_DOC_START_MARKER);
        ObjectMapper mapper = new ObjectMapper(yamlFactory);
        mapper.registerModule(new Jdk8Module());
        mapper.registerModule(new JavaTimeModule());

        Order order = new Order();
        order.setOrderNo("A001");
        order.setDate(LocalDate.now());
        order.setCustomer(new Customer());
        order.getCustomer().setId("id-1");
        order.getCustomer().setName("Davis, ZAN");
        order.setOrderLines(Collections.singletonList(new OrderLine()));
        order.getOrderLines().get(0).setItem("Item 1");
        order.getOrderLines().get(0).setUnitPrice(BigDecimal.valueOf(23.34));
        order.getOrderLines().get(0).setQuantity(100);

        System.out.println(mapper.writeValueAsString(order));
    }
}
