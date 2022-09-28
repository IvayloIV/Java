package softuni.course.mapping.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private Double salary;

    @NonNull
    private LocalDate birthday;

    @NonNull
    private String address;

    @NonNull
    private Boolean onHoliday;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Employee manager;

    @OneToMany(mappedBy = "manager")
    private List<Employee> employees = new ArrayList<>();
}
