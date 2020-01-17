package panda.domain.models.services;

import java.math.BigDecimal;
import java.util.Date;

public class ReceiptServiceModel {

    private String id;

    private BigDecimal fee;

    private Date issuedOn;

    private UserServiceModel recipient;

    private PackageServiceModel receiptPackage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public UserServiceModel getRecipient() {
        return recipient;
    }

    public void setRecipient(UserServiceModel recipient) {
        this.recipient = recipient;
    }

    public PackageServiceModel getReceiptPackage() {
        return receiptPackage;
    }

    public void setReceiptPackage(PackageServiceModel receiptPackage) {
        this.receiptPackage = receiptPackage;
    }
}
