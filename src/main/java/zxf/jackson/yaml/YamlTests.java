package zxf.jackson.yaml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import zxf.jackson.json.JacksonJsonTest;
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
        File file = new File(JacksonJsonTest.class.getClassLoader().getResource("yaml/example.yml").toURI());
        Order order1 = mapper.readValue(file, Order.class);
        System.out.println("Read-1: " + order1);

        Map order2 = mapper.readValue(file, Map.class);
        System.out.println("Read-2: " + order2);
    }

    private static void testWriteYaml() throws IOException, URISyntaxException {
        YAMLFactory  yamlFactory = new YAMLFactory();
        yamlFactory.disable(WRITE_DOC_START_MARKER);
        ObjectMapper mapper = new ObjectMapper(yamlFactory);
        mapper.registerModule(new Jdk8Module());
        mapper.registerModule(new JavaTimeModule());

        Order order = new Order();
        order.setOrderNo("A001");
        order.setDate(LocalDate.now());
        order.setCustomerName("Davis, ZAN");
        OrderLine orderLine = new OrderLine();
        orderLine.setItem("Item 1");
        orderLine.setUnitPrice(BigDecimal.valueOf(23.34));
        orderLine.setQuantity(100);
        order.setOrderLines(Collections.singletonList(orderLine));

        System.out.println(mapper.writeValueAsString(order));
    }
}
