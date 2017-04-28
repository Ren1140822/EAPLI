/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import javax.persistence.*;

@Entity
public class Menu {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long pk;
    @Version
    private Long version;

    @OneToOne
    private Meal meal;
    private boolean published;

    protected Menu() {} //for ORM

    public Menu(Meal meal) {
        if(meal == null){
            throw new IllegalStateException();
        }
        this.meal = meal;
        this.published = true;
    }

    public boolean isPublished() {
        return this.published;
    }

    public Long pk(){
        return pk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Menu menu = (Menu) o;

        if (published != menu.published) return false;
        if (!pk.equals(menu.pk)) return false;
        if (!version.equals(menu.version)) return false;
        return meal.equals(menu.meal);
    }

    @Override
    public int hashCode() {
        int result = pk.hashCode();
        result = 31 * result + version.hashCode();
        result = 31 * result + meal.hashCode();
        result = 31 * result + (published ? 1 : 0);
        return result;
    }
}
