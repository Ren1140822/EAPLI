/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.cafeteria;

import eapli.ecafeteria.application.ConfigurateUserAlertsLimitController;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.io.Console;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diogo Santos
 */
public class ConfigurateUserAlertsLimitUI extends AbstractUI {

    private final ConfigurateUserAlertsLimitController theController = new ConfigurateUserAlertsLimitController();

    @Override
    protected boolean doShow() {
        boolean flag = false;
        int limit = 0;
        while (!flag) {
            try {
                limit = Console.readInteger("Alert's limit: ");
                flag = true;
            } catch (NumberFormatException e) {
                System.out.println("You must insert a valid number.");
            }
        }
        try {
            theController.changeUserAlertsLimit(limit);
            System.out.println("User alert limit change sucessfully.");
        } catch (IOException ex) {
            Logger.getLogger(ConfigurateUserAlertsLimitUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String headline() {
        return "Configurate User Alert's limit";
    }

}
