package zxf.jackson.xml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import zxf.jackson.xml.bean.JsonPhoneDetails;
import zxf.jackson.xml.bean.XMLPhoneDetails;

import java.util.Arrays;

public class JacksonXmlTest {
    public static void main(String[] args) throws JsonProcessingException {
        testXmlBean();
        testJsonBean();
    }
    private static void testXmlBean() throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        XMLPhoneDetails.Manufacturer manufacturer = new XMLPhoneDetails.Manufacturer("OnePlus XML", "China", Arrays.asList("OnePlus 6T", "OnePlus 5T", "OnePlus 5"));
        XMLPhoneDetails phoneDetails = new XMLPhoneDetails("OnePlus XML ", "<6.4>", "6/64 GB", manufacturer);
        String output = xmlMapper.writeValueAsString(phoneDetails);
        System.out.println(output);

        XMLPhoneDetails phoneDetails2 = xmlMapper.readValue(output, XMLPhoneDetails.class);
        System.out.println(phoneDetails2.getName());
    }
    private static void testJsonBean() throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        JsonPhoneDetails.Manufacturer manufacturer = new JsonPhoneDetails.Manufacturer("OnePlus JSON", "China", Arrays.asList("OnePlus 6T", "OnePlus 5T", "OnePlus 5"));
        JsonPhoneDetails phoneDetails = new JsonPhoneDetails("OnePlus JSON", "<6.4>", "6/64 GB", manufacturer);
        String output = xmlMapper.writeValueAsString(phoneDetails);
        System.out.println(output);

        JsonPhoneDetails phoneDetails2 = xmlMapper.readValue(output, JsonPhoneDetails.class);
        System.out.println(phoneDetails2.getName());
    }
}
