package Car_Shop_Extended.Interfaces;

import java.io.Serializable;

public interface Car extends Serializable {
    public final static Integer TIRES = 4;

    public abstract String getModel();

    public abstract String getColor();

    public abstract Integer getHorsePower();

    public abstract String countryProduced();
}
