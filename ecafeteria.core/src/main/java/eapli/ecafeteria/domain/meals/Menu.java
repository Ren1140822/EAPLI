/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import eapli.framework.domain.TimePeriod2;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

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

    @Embedded
    private TimePeriod2 period;

    protected Menu() {
    } //for ORM

    public Menu(TimePeriod2 period) {
        if(period == null){
            throw new IllegalStateException();
        }
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

    public boolean toogleState() {
      this.published=true;
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
