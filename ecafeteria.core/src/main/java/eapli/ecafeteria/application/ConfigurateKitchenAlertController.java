/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.Application;
import eapli.util.io.Files;
import java.io.IOException;

/**
 *
 * @author Diana Silva (1151088@isep.ipp.pt)
 */
public class ConfigurateKitchenAlertController {
    
    private AppSettings settings;
    private final String filePathUser="../user.consoleapp/src/main/resources/ecafeteria.properties";
    private final String filePathBack="../backoffice.consoleapp/src/main/resources/ecafeteria.properties";
    
    public Iterable<String> showAlertTypes(){
        return settings.getKitchenAlertsTypes();
    }
    
    public void changeRedAlertLimit(int percentage) throws IOException {
        settings = Application.settings();
        settings.changeKitchenRedAlertLimit(percentage);
        Files.updateProperty(AppSettings.KITCHEN_RED_ALERT, String.valueOf(percentage), filePathUser);
        Files.updateProperty(AppSettings.KITCHEN_RED_ALERT, String.valueOf(percentage), filePathBack);
    }
    
    public void changeYellowAlertLimit(int percentage) throws IOException {
        settings = Application.settings();
        settings.changeKitchenYellowAlertLimit(percentage);
        Files.updateProperty(AppSettings.KITCHEN_YELLOW_ALERT, String.valueOf(percentage), filePathUser);
        Files.updateProperty(AppSettings.KITCHEN_YELLOW_ALERT, String.valueOf(percentage), filePathBack);
  
    }
}
