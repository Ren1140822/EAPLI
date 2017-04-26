package eapli.ecafeteria.domain.meals;

import eapli.framework.domain.Designation;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Allergen implements Serializable {

    // ORM primary key
    @Id
    @GeneratedValue
    private Long pk;
    @Version
    private Long version;

    @Column(unique = true)
    private Designation name;
    private String description;

    public Allergen(Designation name, String description){
        if(name==null | description==null){
            throw new IllegalStateException("Allergen name or description cannot be null");
        }
        this.name = name;
        this.description = description;
    }

    protected Allergen() {
        // for ORM only
    }
}