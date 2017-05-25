/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.pos.consoleapp.presentation;

import eapli.ecafeteria.application.delivery.RegisterMealDeliveryController;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.io.Console;

/**
 *
 * @author Renato Oliveira 1140822@isep.ipp.pt
 */
public class RegisterMealDeliveryUI extends AbstractUI {

    private RegisterMealDeliveryController controller;

    @Override
    protected boolean doShow() {

        final String mecanographicNumberString = Console
                .readLine("Enter the Mecanographic Number to register meal delivery: ");
        controller = new RegisterMealDeliveryController(mecanographicNumberString);
        System.out.println(this.controller.registerMealDelivery() ? "Meal delivery registered sucessfully." : "No bookings in the right state of this user were found for this user OR cash register is closed. Operation failed.");
        return false;
    }

    @Override
    public String headline() {
        return "Register meal delivery";
    }

}
