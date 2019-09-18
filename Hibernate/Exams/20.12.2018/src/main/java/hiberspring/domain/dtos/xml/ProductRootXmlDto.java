package hiberspring.domain.dtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductRootXmlDto implements Serializable {
    @XmlElement(name = "product")
    private List<ProductXmlDto> productsDto;

    public List<ProductXmlDto> getProductsDto() {
        return productsDto;
    }

    public void setProductsDto(List<ProductXmlDto> productsDto) {
        this.productsDto = productsDto;
    }
}
