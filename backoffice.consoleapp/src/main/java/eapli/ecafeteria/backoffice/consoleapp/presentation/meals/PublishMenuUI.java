/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.application.meals.PublishMenuController;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.ecafeteria.domain.meals.*;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.SelectWidget;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eduangelo Ferreira
 */
public class PublishMenuUI extends AbstractUI {

    private final PublishMenuController theController = new PublishMenuController();

    protected Controller controller() {

        return this.theController;
    }

    @Override
    protected boolean doShow() {

        final Iterable<Menu> listMenu = this.theController.allNotPublishedMenu();
        if (!listMenu.iterator().hasNext()) {
            System.out.println("There are no registered Menu");
        } else {

            final SelectWidget<Menu> selector = new SelectWidget<>("Menu:", listMenu, new MenuPrinter());
            selector.show();
            final Menu updtMenu = selector.selectedElement();

            try {
                this.theController.publishMenu(updtMenu);
            } catch (DataConcurrencyException ex) {
                System.out.println("It is not possible to publish menu because it was changed by another user");
            } catch (DataIntegrityViolationException ex) {
                // should not happen!
                Logger.getLogger(ActivateDeactivateDishTypeUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return true;
    }

    @Override
    public String headline() {
        
        return "Publish Menu";
    }

}
