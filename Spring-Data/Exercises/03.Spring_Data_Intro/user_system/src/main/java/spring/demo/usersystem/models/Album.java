package spring.demo.usersystem.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "albums")
@Data
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @Column(name = "background_color")
    private String backgroundColor;

    @Column(name = "is_public")
    private Boolean isPublic;

    @ManyToOne
    private User user;

    @ManyToMany
    @JoinTable(
        name = "albums_pictures",
        joinColumns = @JoinColumn(name = "album_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "picture_id", referencedColumnName = "id")
    )
    private Set<Picture> pictures;
}
