package heroes.domain.entities;

import heroes.domain.enums.HeroClass;

import javax.persistence.*;

@Entity
@Table(name = "hero")
public class Hero extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "class", nullable = false)
    @Enumerated(EnumType.STRING)
    private HeroClass heroClass;

    @Column(name = "level", nullable = false)
    private Integer level;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroClass getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(HeroClass heroClass) {
        this.heroClass = heroClass;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
