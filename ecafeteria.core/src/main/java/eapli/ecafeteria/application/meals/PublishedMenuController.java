/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.domain.meals.Menu;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 * @todo is there a typo in the name of the class? should it be PublishMenu?
 * @author Eduangelo Ferreira
 */
public class PublishedMenuController implements Controller {

    private ListMenuService listMenu = new ListMenuService();
    private MenuRepository menuRepository = PersistenceContext.repositories().menus();

    //TODO is there a typo in the name of the method? should it be publishMenu?
    public void publishedMenu(Menu menu) throws DataConcurrencyException, DataIntegrityViolationException {
        if (menu == null) {
            throw new IllegalStateException();
        }
        menu.toogleState();
        menuRepository.save(menu);
    }

    public Iterable<Menu> allNotPublishedMenu() {

        return this.menuRepository.notPublishedMenu();
    }

}
