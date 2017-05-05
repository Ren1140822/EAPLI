/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import eapli.ecafeteria.domain.cafeteria.OrganicUnit;
import eapli.framework.domain.TimePeriod2;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * @FIXME javadoc
 * @FIXME is this an entity, a value object or an aggregate?
 */
@Entity
public class Menu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk;
    @Version
    private Long version;

    @OneToMany
    private Set<Meal> meals;
    private boolean published;

    @OneToOne
    private OrganicUnit organicUnit;

    @Embedded
    private TimePeriod2 period;

    protected Menu() {
    } //for ORM

    public Menu(TimePeriod2 period, OrganicUnit organicUnit) {
        if(period == null || organicUnit == null){
            throw new IllegalStateException();
        }
        this.organicUnit = organicUnit;
        this.meals = new HashSet<>();
        this.published = true;
        this.period = period;
    }

    public boolean isPublished() {
        return this.published;
    }

    public Long pk() {
        return pk;
    }

    public boolean addMeal(Meal meal) {
        return meals.add(meal);
    }

    public Iterable<Meal> getMeals() {
        return meals;
    }

    public OrganicUnit organicUnit(){
        return organicUnit;
    }

    public boolean toogleState() {
        this.published = true;
        return isPublished();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Menu menu = (Menu) o;

        if (published != menu.published) {
            return false;
        }

        if (!pk.equals(menu.pk)) {
            return false;
        }

        if (!version.equals(menu.version)) {
            return false;
        }

        return meals.containsAll(menu.meals);
    }

    @Override
    public int hashCode() {
        int result = meals.hashCode();
        result = 31 * result + (published ? 1 : 0);
        return result;
    }
}
