package models.shampoos;

import models.labels.BasicLabel;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "FS")
public class FiftyShade extends BasicShampoo {

    public FiftyShade(BasicLabel basicLabel) {
        super("Fifty Shades", 6.69, ShampooSize.SMALL, basicLabel);
    }
}
