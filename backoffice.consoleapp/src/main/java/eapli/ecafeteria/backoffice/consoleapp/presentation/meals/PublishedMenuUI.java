/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.application.meals.PublishedMenuController;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.ecafeteria.domain.meals.*;
import eapli.framework.presentation.console.SelectWidget;


/**
 *
 * @author Eduangelo Ferreira
 */
public class PublishedMenuUI extends AbstractUI {

    private final PublishedMenuController theController = new PublishedMenuController();

    protected Controller controller() {
      
        return this.theController;
    }

    @Override
    protected boolean doShow() {
      
       final Iterable<Menu> listMenu=this.theController.allNotPublishedMenu();
      // final SelectWidget<Menu> selector=new SelectWidget<>("Menu:",listMenu,new MenuPrinter());
      // selector.show();
        return true;
    }

    @Override
    public String headline() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
