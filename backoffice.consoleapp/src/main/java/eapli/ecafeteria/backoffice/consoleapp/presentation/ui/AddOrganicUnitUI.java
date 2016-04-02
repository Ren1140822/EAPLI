/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.ui;

import eapli.ecafeteria.application.AddOrganicUnitController;
import eapli.ecafeteria.domain.OrganicUnit;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.Console;

/**
 *
 * @author arocha
 */
public class AddOrganicUnitUI extends AbstractUI {
    private final AddOrganicUnitController theController = new AddOrganicUnitController();

    @Override
    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
      final String acronym = Console.readLine("Acronym");
        final String name = Console.readLine("Name");
        final String description = Console.readLine("Description");

        final OrganicUnit organicUnit = this.theController.addOrganicUnit(acronym, name, description);

        return false;
    }

    @Override
    public String headline() {
        return "Add Organic Unit";  
    }
    
}
