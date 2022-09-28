package models;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "store_locations")
@Data
public class StoreLocation extends Identity {

    @Column(name = "location_name", nullable = false)
    private String locationName;

    @OneToMany(mappedBy = "storeLocation")
    private Set<Sale> sales;
}
