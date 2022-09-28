package spring.demo.usersystem.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "towns")
@Data
public class Town {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;
}
