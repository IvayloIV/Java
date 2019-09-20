package app.ccb.domain.dtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cards")
@XmlAccessorType(XmlAccessType.FIELD)
public class CardRootXmlDto {
    @XmlElement(name = "card")
    private List<CardXmlDto> cardXmlDtos;

    public List<CardXmlDto> getCardXmlDtos() {
        return cardXmlDtos;
    }

    public void setCardXmlDtos(List<CardXmlDto> cardXmlDtos) {
        this.cardXmlDtos = cardXmlDtos;
    }
}
