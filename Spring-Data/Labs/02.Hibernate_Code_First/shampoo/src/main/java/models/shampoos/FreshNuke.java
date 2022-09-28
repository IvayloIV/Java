package models.shampoos;

import models.labels.BasicLabel;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "FN")
public class FreshNuke extends BasicShampoo {

    public FreshNuke(BasicLabel basicLabel) {
        super("Fresh Nuke", 9.33, ShampooSize.LARGE, basicLabel);
    }
}
