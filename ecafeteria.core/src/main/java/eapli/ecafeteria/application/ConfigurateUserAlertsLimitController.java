package eapli.ecafeteria.application;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.Application;

/**
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class ConfigurateUserAlertsLimitController {
 
    private AppSettings settings;
    
    public void changeUserAlertsLimit(int multiplicationFactor){
        settings = Application.settings();
        settings.changeUserAlertsLimit(multiplicationFactor);
    }
    
    
}
