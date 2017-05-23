/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.cafeteria.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Menu;
import eapli.framework.persistence.repositories.DataRepository;

import java.util.Calendar;

/**
 *
 * @author Eduangelo Ferreira
 */
public interface MenuRepository extends DataRepository<Menu, Long> {

    public Menu findByPk(Long pk);
    public Iterable<Menu> publishedMenu();
    public Iterable<Menu> publishedMenu(CafeteriaUser user);
    public Iterable<Menu> notPublishedMenu();
    
    /**
     * It gives an iterable of published menus from one user.
     *
     * @param user The Cafeteria user.
     * @return It returns an iterable of menus.
     */
    public Iterable<Menu> publishedMenusOfDay(Calendar day,CafeteriaUser user);
}
