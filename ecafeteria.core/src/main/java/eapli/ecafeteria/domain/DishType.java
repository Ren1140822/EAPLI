package eapli.ecafeteria.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import eapli.framework.domain.AggregateRoot;

/**
 * Created by MCN on 29/03/2016.
 */
@Entity
public class DishType implements AggregateRoot<String>, Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String acronym;
    private String description;
    private boolean active;

    protected DishType() {
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
        return this.active;
    }

    public void changeDishTypeState() {

        this.active = !this.active;
    }

    public void changeDescriptionTo(String newDescription) {

        this.description = newDescription;
    }

    @Override
    public String id() {
        return this.acronym;
    }

    @Override
    public boolean is(String id) {
        return id.equalsIgnoreCase(this.acronym);
    }

}
