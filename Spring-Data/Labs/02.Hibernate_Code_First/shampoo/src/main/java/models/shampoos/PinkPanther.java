package models.shampoos;

import models.labels.BasicLabel;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "PP")
public class PinkPanther extends BasicShampoo {

    public PinkPanther(BasicLabel basicLabel) {
        super("Pink Panther", 8.5, ShampooSize.MEDIUM, basicLabel);
    }
}
