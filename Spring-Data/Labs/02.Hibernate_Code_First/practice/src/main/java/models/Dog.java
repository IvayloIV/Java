package models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
public class Dog extends Animal {

    @Column
    private Integer jumpPower;

    public Integer getJumpPower() {
        return jumpPower;
    }

    public void setJumpPower(Integer jumpPower) {
        this.jumpPower = jumpPower;
    }
}
