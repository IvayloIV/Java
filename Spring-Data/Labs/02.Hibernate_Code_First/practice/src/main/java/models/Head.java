package models;

import javax.persistence.*;

@Entity
@Table(name = "head")
public class Head {

    @Id
    @Column(name = "head_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long innerId;

    private Integer weight;

    @OneToOne(mappedBy = "head")
    private Cat cat;

    public Long getInnerId() {
        return innerId;
    }

    public void setInnerId(Long innerId) {
        this.innerId = innerId;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }
}
