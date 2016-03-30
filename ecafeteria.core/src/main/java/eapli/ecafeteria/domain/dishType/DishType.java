package eapli.ecafeteria.domain.dishType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by MCN on 29/03/2016.
 */
@Entity
public class DishType implements DomainEntity<ID>{
    @Id
    @GeneratedValue
    private long id;

    private String name;
    private String description;
    private boolean active;

    public DishType() {
    }

    public DishType(String name, String description) {
        this.name = name;
        this.description = description;
        this.active = true;
    }


    public String description() {
        return this.description;
    }

    public boolean isActive() {
        return active;
    }
}
