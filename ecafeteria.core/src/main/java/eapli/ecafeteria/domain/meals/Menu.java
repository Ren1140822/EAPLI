/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import java.util.Objects;
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
    private MenuEntry menuEntry;
    private boolean published;

    protected Menu() {} //for ORM

    public Menu(MenuEntry menuEntry) {
        if(menuEntry == null){
            throw new IllegalStateException();
        }
        this.menuEntry = menuEntry;
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
        return menuEntry.equals(menu.menuEntry);
    }

    @Override
    public int hashCode() {
        int result = pk.hashCode();
        result = 31 * result + version.hashCode();
        result = 31 * result + menuEntry.hashCode();
        result = 31 * result + (published ? 1 : 0);
        return result;
    }
}
