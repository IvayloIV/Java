package panda.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "receipt")
public class Receipt extends BaseEntity {

    @Column(name = "fee")
    private BigDecimal fee;

    @Column(name = "issued_on")
    private Date issuedOn;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "recipient_id", referencedColumnName = "id")
    private User recipient;

    @ManyToOne(targetEntity = Package.class)
    @JoinColumn(name = "package_id", referencedColumnName = "id")
    private Package receiptPackage;

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public Date getIssuedOn() {
        return issuedOn;
    }

    public void setIssuedOn(Date issuedOn) {
        this.issuedOn = issuedOn;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public Package getReceiptPackage() {
        return receiptPackage;
    }

    public void setReceiptPackage(Package receiptPackage) {
        this.receiptPackage = receiptPackage;
    }
}
