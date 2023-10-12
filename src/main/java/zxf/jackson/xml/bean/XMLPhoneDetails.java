package zxf.jackson.xml.bean;

import com.fasterxml.jackson.dataformat.xml.annotation.*;

import java.util.List;

@JacksonXmlRootElement(localName = "phoneDetails")
public class XMLPhoneDetails {
    @JacksonXmlText
    private String name;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "display_size")
    private String displaySize;

    @JacksonXmlProperty(localName = "internal_memory", isAttribute = true)
    private String memory;

    @JacksonXmlProperty(localName = "manufacturer")
    private XMLPhoneDetails.Manufacturer manufacturer;

    public XMLPhoneDetails() {

    }

    public XMLPhoneDetails(String name, String displaySize, String memory, XMLPhoneDetails.Manufacturer manufacturer) {
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

        @JacksonXmlProperty(localName = "manufacturer_name", isAttribute = true)
        private String name;

        @JacksonXmlProperty(localName = "country", isAttribute = true)
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
