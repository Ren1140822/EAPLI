/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.cafeteria;

import eapli.framework.actions.Action;

/**
 *
 * @author Diana Silva (1151088@isep.ipp.pt)
 */
public class ConfigurateKitchenAlertsLimitAction implements Action {

    @Override
    public boolean execute() {
        return new ConfigurateKitchenAlertsLimitsUI().show();
    }
    
    
}
