package bg.softuni.mobilelele.entities;

import bg.softuni.mobilelele.entities.enums.EngineEnum;
import bg.softuni.mobilelele.entities.enums.TransmissionEnum;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "offers")
public class OfferEntity extends BaseEntity{

    @Column(columnDefinition="TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private EngineEnum engine;

    private String imageUrl;

    private Integer mileage;

    @Column(nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private TransmissionEnum transmission;

    private Integer year;

    @ManyToOne(targetEntity = ModelEntity.class)
    @JoinColumn(name = "model_id", referencedColumnName = "id")
    private ModelEntity model;

    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EngineEnum getEngine() {
        return engine;
    }

    public void setEngine(EngineEnum engine) {
        this.engine = engine;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public void setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public ModelEntity getModel() {
        return model;
    }

    public void setModel(ModelEntity model) {
        this.model = model;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
