package models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@DiscriminatorValue("CC")
public class CreditCard extends BillingDetails {

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "expiration_mouth")
    private Integer expirationMouth;

    @Column(name = "expiration_year")
    private Integer expirationYear;
}
