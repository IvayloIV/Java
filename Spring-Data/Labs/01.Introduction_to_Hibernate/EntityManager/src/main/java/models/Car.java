package models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cars")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Basic(fetch = FetchType.LAZY)
    private String brand;

    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date")
    private Date creationDate;
}
