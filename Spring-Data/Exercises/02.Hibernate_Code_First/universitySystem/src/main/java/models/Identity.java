package models;

import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
public abstract class Identity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
}
