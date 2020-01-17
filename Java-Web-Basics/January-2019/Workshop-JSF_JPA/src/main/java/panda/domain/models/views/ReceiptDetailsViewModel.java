package panda.domain.models.views;

import java.math.BigDecimal;
import java.util.Date;

public class ReceiptDetailsViewModel {

    private String id;

    private BigDecimal fee;

    private Date issuedOn;

    private String recipientUsername;

    private PackageDetailsViewModel receiptPackage;

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

    public String getRecipientUsername() {
        return recipientUsername;
    }

    public void setRecipientUsername(String recipientUsername) {
        this.recipientUsername = recipientUsername;
    }

    public PackageDetailsViewModel getReceiptPackage() {
        return receiptPackage;
    }

    public void setReceiptPackage(PackageDetailsViewModel receiptPackage) {
        this.receiptPackage = receiptPackage;
    }
}
