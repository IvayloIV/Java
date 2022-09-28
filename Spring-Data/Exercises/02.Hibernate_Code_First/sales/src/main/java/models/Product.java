package models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "products")
@Data
public class Product extends Identity {

    @Column(nullable = false)
    private String name;

    @Column
    private Integer quantity;

    @Column
    private BigDecimal price;

    @OneToMany(mappedBy = "product")
    private Set<Sale> sales;
}
