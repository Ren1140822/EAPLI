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
 * The controller to publish a menu in the system
 *
 * @author Eduangelo Ferreira
 */
public class PublishMenuController implements Controller {

    private ListMenuService listMenu = new ListMenuService();
    private MenuRepository menuRepository = PersistenceContext.repositories().menus();

    /**
     * This method is to receive an unpublished menu, with the purpose of publishing it
     * @param menu The menu to be publish.
     * @throws DataConcurrencyException
     * @throws DataIntegrityViolationException
     */
    public void publishMenu(Menu menu) throws DataConcurrencyException, DataIntegrityViolationException {
        if (menu == null) {
            throw new IllegalStateException();
        }
        menu.publish();
        menuRepository.save(menu);
    }

    /**
     * Provides all unpublished menus
     *
     * @return Unpublished list of menus
     */
    public Iterable<Menu> allNotPublishedMenu() {

        return this.menuRepository.notPublishedMenu();
    }

}
