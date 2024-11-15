package zxf.jackson.yaml.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Order {
    private String orderNo;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String customerName;
    private List<OrderLine> orderLines;
}