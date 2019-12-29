package fdmc.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "cat")
public class Cat extends BaseEntity {
    @Column(name = "name", nullable = false)
    @Size(min = 2, max = 10)
    private String name;

    @Column(name = "breed", nullable = false)
    @Size(min = 5, max = 20)
    private String breed;

    @Column(name = "color")
    private String color;

    @Column(name = "age", nullable = false)
    @Min(1)
    @Max(31)
    private Integer age;

    @Column(name = "gender")
    private String gender;

    @Column(name = "price", nullable = false)
    @DecimalMin("0.01")
    private Double price;

    @Column(name = "added_on")
    private Date addedOn;

    @Column(name = "has_passport")
    private Boolean hasPassport;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Date addedOn) {
        this.addedOn = addedOn;
    }

    public Boolean getHasPassport() {
        return hasPassport;
    }

    public void setHasPassport(Boolean hasPassport) {
        this.hasPassport = hasPassport;
    }
}
