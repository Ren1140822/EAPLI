/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.cafeteria;

import eapli.ecafeteria.application.ConfigurateUserAlertsLimitController;
import eapli.framework.presentation.console.AbstractUI;
import eapli.util.io.Console;

/**
 *
 * @author Diogo Santos
 */
public class ConfigurateUserAlertsLimitUI extends AbstractUI {

    private final ConfigurateUserAlertsLimitController theController = new ConfigurateUserAlertsLimitController();

    @Override
    protected boolean doShow() {
        int limit = Console.readInteger("Alert's limit: ");
        theController.changeUserAlertsLimit(limit);
        return false;
    }

    @Override
    public String headline() {
        return "Configurate User Alert's limit";
    }

}
