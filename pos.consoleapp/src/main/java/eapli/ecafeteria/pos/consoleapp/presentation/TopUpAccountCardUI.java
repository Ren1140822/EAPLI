/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.pos.consoleapp.presentation;

import eapli.ecafeteria.application.delivery.TopUpAccountCardController;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.io.Console;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Ivo Ferro 1151159
 * @author Daniel Gonçalves 1151452
 */
public class TopUpAccountCardUI extends AbstractUI {

    private final TopUpAccountCardController theController = new TopUpAccountCardController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {

        final String mecanographicNumberString = Console
                .readLine("Enter the Mecanographic Number to TopUp: ");

        final Double eurosValue = Console
                .readDouble("TopUp Amount (in Euros): ");

        try {
            this.theController.topUpCard(mecanographicNumberString, eurosValue);
        } catch (DataConcurrencyException ex) {
            System.out.println("That entity has already been changed or deleted since you last read it");
            Logger.getLogger(TopUpAccountCardUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataIntegrityViolationException ex) {
            System.out.println("That entity ID is already in use");
            Logger.getLogger(TopUpAccountCardUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public String headline() {
        return "Change Dish Type description";
    }
}
