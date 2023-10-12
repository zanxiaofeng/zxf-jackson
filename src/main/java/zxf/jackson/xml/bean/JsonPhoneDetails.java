package zxf.jackson.xml.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.List;

@JsonPropertyOrder({"internal_memory", "phone_name", "internal_memory"})
public class JsonPhoneDetails {
    @JsonProperty("phone_name")
    private String name;

    @JsonProperty("display_size")
    private String displaySize;

    @JsonProperty("internal_memory")
    private String memory;

    @JsonProperty("manufacturer")
    private Manufacturer manufacturer;

    public JsonPhoneDetails() {

    }

    public JsonPhoneDetails(String name, String displaySize, String memory, Manufacturer manufacturer) {
        this.name = name;
        this.displaySize = displaySize;
        this.memory = memory;
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplaySize() {
        return displaySize;
    }

    public void setDisplaySize(String displaySize) {
        this.displaySize = displaySize;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public static class Manufacturer {
        @JsonProperty("manufacturer_name")
        private String name;

        @JsonProperty("country")
        private String country;

        @JacksonXmlElementWrapper(localName = "other_phones")
        private List<String> phone;

        public Manufacturer() {
        }

        public Manufacturer(String name, String country, List<String> phones) {
            this.name = name;
            this.country = country;
            this.phone = phones;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public List<String> getPhone() {
            return phone;
        }

        public void setPhone(List<String> phone) {
            this.phone = phone;
        }
    }
}
