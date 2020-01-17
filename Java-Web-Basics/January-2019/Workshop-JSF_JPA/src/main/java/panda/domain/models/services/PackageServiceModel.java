package panda.domain.models.services;

import panda.domain.entities.Receipt;
import panda.domain.enums.Status;

import java.util.Date;
import java.util.List;

public class PackageServiceModel {

    private String id;

    private String description;

    private Double weight;

    private String shippingAddress;

    private Status status;

    private Date deliveryDate;

    private UserServiceModel recipient;

    private List<Receipt> receipts;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public UserServiceModel getRecipient() {
        return recipient;
    }

    public void setRecipient(UserServiceModel recipient) {
        this.recipient = recipient;
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
    }
}
