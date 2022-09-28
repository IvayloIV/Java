package models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class User extends Identity {

    @Column(name = "first_name", length = 80)
    private String firstName;

    @Column(name = "last_name", length = 80, nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 62)
    private String password;

    @OneToOne(mappedBy = "owner")
    private BillingDetails billingDetails;
}
