package spring.demo.usersystem.models;

import lombok.*;
import spring.demo.usersystem.models.validationAnnotations.Email;
import spring.demo.usersystem.models.validationAnnotations.PasswordConstraint;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NonNull
    @Size(min = 4, max = 30)
    @Column(nullable = false, length = 30)
    private String username;

    @NonNull
    @Column(name = "first_name")
    private String firstName;

    @NonNull
    @Column(name = "last_name")
    private String lastName;

    @Transient
    private String fullName;

    @NotNull
    @NonNull
    @PasswordConstraint(minLength = 6,
            maxLength = 50,
            containsLowercase = true,
            containsUppercase = true,
            containsDigit = true,
            containsSpecialSymbol = true)
    @Column(nullable = false, length = 50)
    private String password;

    @Email
    @NonNull
    @Column(nullable = false)
    private String email;

    @Column(name = "registered_on")
    private LocalDateTime registeredOn;

    @Column(name = "last_time_logged_in")
    private LocalDateTime lastTimeLoggedIn;

    @Min(1)
    @Max(120)
    @NonNull
    private Integer age;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @ManyToOne
    private Town bornTown;

    @ManyToOne
    private Town livingTown;

    @ManyToMany
    @JoinTable(
        name = "users_friends",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id")
    )
    private Set<User> friends;

    @OneToMany(mappedBy = "user")
    private Set<Album> albums;

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @PrePersist
    public void prePersist() {
        registeredOn = LocalDateTime.now();
        lastTimeLoggedIn = LocalDateTime.now();
        isDeleted = false;
    }
}
