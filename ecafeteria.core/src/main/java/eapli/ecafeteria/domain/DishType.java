package eapli.ecafeteria.domain;

import eapli.framework.domain.AggregateRoot;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by MCN on 29/03/2016.
 */
@Entity
public class DishType implements AggregateRoot<String>, Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String acronym;
    private String description;
    private boolean active;

    public DishType() {
    }

    public DishType(String name, String description) {
        this.acronym = name;
        this.description = description;
        this.active = true;
    }


    public String description() {
        return this.description;
    }

    public boolean isActive() {
        return active;
    }

    public void changeDishTypeState(){
        if(this.isActive()){
            this.active=false;
        }
        else{
            this.active=true;
        }
    }

    @Override
    public String id() {
        return this.acronym;
    }

    @Override
    public boolean is(String id) {
        return id.equalsIgnoreCase(acronym);
    }

  
}
