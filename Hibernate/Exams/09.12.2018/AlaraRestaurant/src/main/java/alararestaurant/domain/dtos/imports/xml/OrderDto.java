package alararestaurant.domain.dtos.imports.xml;

import alararestaurant.domain.entities.OrderType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class OrderDto {
    @XmlElement
    private String customer;

    @XmlElement(name = "employee")
    private String employeeName;

    @XmlElement(name = "date-time")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private LocalDateTime dateTime;

    @XmlElement
    private OrderType type;

    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    private List<ItemXmlDto> items;

    public OrderDto() {
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public List<ItemXmlDto> getItems() {
        return items;
    }

    public void setItems(List<ItemXmlDto> items) {
        this.items = items;
    }
}
