package bg.softuni.mobilelele.entities;

import bg.softuni.mobilelele.entities.enums.ModelCategoryEnum;

import javax.persistence.*;

@Entity
@Table(name = "models")
public class ModelEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private ModelCategoryEnum category;

    @Column(length = 512)
    private String imageUrl;

    private Integer startYear;

    private Integer endYear;

    @ManyToOne(targetEntity = BrandEntity.class)
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private BrandEntity brand;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ModelCategoryEnum getCategory() {
        return category;
    }

    public void setCategory(ModelCategoryEnum category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public void setBrand(BrandEntity brand) {
        this.brand = brand;
    }
}
