package Sales_Database.entities;

import javax.persistence.Basic;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Nameable extends Identity {
    @Basic
    private String name;

    public Nameable() {
    }

    public Nameable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
