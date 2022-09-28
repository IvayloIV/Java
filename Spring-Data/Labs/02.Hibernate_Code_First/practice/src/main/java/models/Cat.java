package models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cat extends Animal {

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "head", referencedColumnName = "head_id")
    private Head head;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id", referencedColumnName = "id")
    private Food food;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "cat_legs",
        joinColumns = @JoinColumn(name = "cat_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "leg_id", referencedColumnName = "id")
    )
    private Set<Leg> legs = new HashSet<Leg>();

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Set<Leg> getLegs() {
        return legs;
    }

    public void setLegs(Set<Leg> legs) {
        this.legs = legs;
    }
}
