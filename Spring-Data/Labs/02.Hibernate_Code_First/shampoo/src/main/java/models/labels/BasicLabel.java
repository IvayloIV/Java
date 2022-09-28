package models.labels;

import models.Identity;
import models.shampoos.BasicShampoo;

import javax.persistence.*;

@Entity
@Table(name = "labels")
public class BasicLabel extends Identity implements Label {

    @Column
    private String subtitle;

    @Column
    private String title;

    @OneToOne(mappedBy = "basicLabel", targetEntity = BasicShampoo.class, cascade = CascadeType.PERSIST)
    private BasicShampoo basicShampoo;

    public BasicLabel() {
    }

    public BasicLabel(String subtitle, String title) {
        this.subtitle = subtitle;
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BasicShampoo getBasicShampoo() {
        return basicShampoo;
    }

    public void setBasicShampoo(BasicShampoo basicShampoo) {
        this.basicShampoo = basicShampoo;
    }
}
