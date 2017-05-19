/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.pos.consoleapp.presentation;

import eapli.ecafeteria.application.delivery.TopUpAccountCardController;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.io.Console;

import javax.persistence.NoResultException;

/**
 * Represents the user interface to topUp a card.
 *
 * @author Ivo Ferro 1151159
 * @author Daniel Gonçalves 1151452
 */
public class TopUpAccountCardUI extends AbstractUI {

    /**
     * The controller to TopUp cards.
     */
    private final TopUpAccountCardController theController = new TopUpAccountCardController();

    @Override
    protected boolean doShow() {

        boolean success = false;

        final String mecanographicNumberString = Console.readLine("Enter the Mecanographic Number to TopUp: ");

        try {
            theController.insertCard(mecanographicNumberString);

            final Double eurosValue = Console.readDouble("TopUp Amount (in Euros): ");
            try {
                this.theController.topUpCard(eurosValue);
                success = !success; // Turn to success to true

            } catch (IllegalStateException ex) {
                System.out.println("The amount to topUp must be positive.");
            } catch (DataConcurrencyException ex) {
                System.out.println("That entity has already been changed or deleted since you last read it");
            } catch (DataIntegrityViolationException ex) {
                System.out.println("That entity ID is already in use");
            }
        } catch (NoResultException ex) {
            System.out.println("The mecanographic number doesn't exists.");
        }

        if (success) {
            System.out.println("TopUp was successful.");
        } else {
            System.out.println("TopUp was unsuccessful.");
        }

        return false;
    }

    @Override
    public String headline() {
        return "TopUp Card";
    }
}
