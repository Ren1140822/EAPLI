/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import eapli.ecafeteria.domain.cafeteria.OrganicUnit;
import eapli.framework.domain.TimePeriod2;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * Represents a menu through the period, organic unit, meal Eduangelo Ferreira
 */
@Entity
public class Menu implements AggregateRoot<TimePeriod2>, Serializable {

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

    /**
     * Build an instance of a menu receiving the period and organic unit
     *
     * @param period period of menu
     * @param organicUnit organic unit of menu
     * @param period
     * @param organicUnit
     */
    public Menu(TimePeriod2 period, OrganicUnit organicUnit) {
        if (period == null || organicUnit == null) {
            throw new IllegalStateException();
        }
        this.organicUnit = organicUnit;
        this.meals = new HashSet<>();
        this.published = false;
        this.period = period;
    }

    /**
     * Allows you to see whether a menu is published or not
     *
     * @return
     */
    public boolean isPublished() {
        return this.published;
    }

    /**
     * Lets you add meal to the menu meal list
     *
     * @param meals meal of menu
     * @return list of meals
     */
    public boolean addAllMeals(Set<Meal> meals) {
        return this.meals.addAll(meals);
    }

    /**
     * Returns the list of meals from a menu
     *
     * @return list of meals
     */
    public Iterable<Meal> getMeals() {
        return new ArrayList<>(meals);
    }

    /**
     * @return Organic Unit of menu
     */
    public OrganicUnit organicUnit() {
        return organicUnit;
    }

    /**
     *
     * @return period of menu
     */
    public TimePeriod2 period() {
        return period;
    }

    /**
     * This method allows you to publish a menu. 
     * From the moment you publish a menu you can not change.
     * @return isPublished()
     */
    public boolean publish() {
        this.published = true;
        return isPublished();
    }

    /**
     * 
     * @param o object
     * @return 
     */
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

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Dish)) {
            return false;
        }

        final Menu that = (Menu) other;
        if (this == that) {
            return true;
        }

        return id().equals(that.id())
                && period.equals(that.period())
                && meals.equals(that.getMeals());
    }

    @Override
    public boolean is(TimePeriod2 id) {
        return id.equals(this.period);
    }

    @Override
    public TimePeriod2 id() {
        return this.period;
    }

}
