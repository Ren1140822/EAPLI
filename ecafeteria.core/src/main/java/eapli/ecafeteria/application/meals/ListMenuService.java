/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.Menu;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 *
 * @author Eduangelo Ferreira
 */
public class ListMenuService {

    private final MenuRepository menuRepository = PersistenceContext.repositories().menus();

    public Iterable<Menu> allNotPublishedMenu() {

        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);
        return this.menuRepository.notPublishedMenu();
    }
}
