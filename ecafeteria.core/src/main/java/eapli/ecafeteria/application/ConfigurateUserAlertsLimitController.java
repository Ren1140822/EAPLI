package eapli.ecafeteria.application;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.Application;
import eapli.util.io.Files;

/**
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class ConfigurateUserAlertsLimitController {

    private AppSettings settings;

    public void changeUserAlertsLimit(int multiplicationFactor) {
        settings = Application.settings();
        settings.changeUserAlertsLimit(multiplicationFactor);
        Files.updateProperty(AppSettings.USER_ALERT_LIMITS, String.valueOf(multiplicationFactor), "C:\\Users\\Diogo Santos\\Documents\\eapliProj\\user.consoleapp\\src\\main\\resources\\ecafeteria.properties");
    }

}
