package app.ccb.domain.dtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "bank-accounts")
@XmlAccessorType(XmlAccessType.FIELD)
public class BankAccountRootXmlDto {
    @XmlElement(name = "bank-account")
    private List<BankAccountXmlDto> bankAccountDtos;

    public List<BankAccountXmlDto> getBankAccountDtos() {
        return bankAccountDtos;
    }

    public void setBankAccountDtos(List<BankAccountXmlDto> bankAccountDtos) {
        this.bankAccountDtos = bankAccountDtos;
    }
}
