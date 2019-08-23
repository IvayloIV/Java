package Hospital_Database.entities;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Identity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public Identity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
