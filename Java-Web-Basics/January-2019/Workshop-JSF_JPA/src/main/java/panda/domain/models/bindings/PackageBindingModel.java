package panda.domain.models.bindings;

import panda.domain.models.views.UserPackageCreateViewModel;

public class PackageBindingModel {

    private String description;

    private Double weight;

    private String shippingAddress;

    private UserPackageCreateViewModel recipient;

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

    public UserPackageCreateViewModel getRecipient() {
        return recipient;
    }

    public void setRecipient(UserPackageCreateViewModel recipient) {
        this.recipient = recipient;
    }
}
