/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import eapli.ecafeteria.domain.authz.*;
import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Menu implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long pk;
    @Version
    private Long version;

    @OneToOne
    private Meal meal;
    @ManyToOne
    private SystemUser systemUser;
    private boolean published;

    protected Menu() {
    } //for ORM

    public Menu(Meal meal, SystemUser systemUser) {
        if (meal == null || systemUser==null) {
            throw new IllegalStateException();
        }
        this.meal = meal;
        this.published = true;
        this.systemUser = systemUser;
    }

    public boolean isPublished() {
        return this.published;
    }

    public Long pk() {
        return pk;
    }

    public SystemUser systemUser() {
        return this.systemUser;
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

        if (published != menu.published)
            return false;

        if (!pk.equals(menu.pk))
            return false;

        if (!version.equals(menu.version))
            return false;

        if (!systemUser.equals(((Menu) o).systemUser))
            return false;

        return meal.equals(menu.meal);
    }

    @Override
    public int hashCode() {
        int result = meal.hashCode();
        result = 31 * result + (published ? 1 : 0);
        return result;
    }
}
