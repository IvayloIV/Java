package models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "billing_details")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public class BillingDetails extends Identity {

    @Column(nullable = false)
    private String number;

    @OneToOne
    @JoinColumn(name = "owner", referencedColumnName = "id")
    private User owner;
}
