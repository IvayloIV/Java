package models;

import lombok.*;

import java.util.Date;

//@Entity
//@Table(name = "cars")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Car {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String brand;

//    @Basic
//    @Temporal(TemporalType.TIME)
//    @Column(name = "creation_date")
    private Date creationDate;
}
