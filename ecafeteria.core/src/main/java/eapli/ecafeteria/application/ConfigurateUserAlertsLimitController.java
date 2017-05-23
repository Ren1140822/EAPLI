package eapli.ecafeteria.application;

import eapli.ecafeteria.AppSettings;
import eapli.ecafeteria.Application;
import eapli.util.io.Files;
import java.io.IOException;

/**
 *
 * @author Sofia Silva [1150690@isep.ipp.pt]
 */
public class ConfigurateUserAlertsLimitController {

    private AppSettings settings;

    public void changeUserAlertsLimit(int multiplicationFactor) throws IOException {
        settings = Application.settings();
        settings.changeUserAlertsLimit(multiplicationFactor);
        Files.updateProperty(AppSettings.USER_ALERT_LIMITS, String.valueOf(multiplicationFactor), "../user.consoleapp/src/main/resources/ecafeteria.properties");
        Files.updateProperty(AppSettings.USER_ALERT_LIMITS, String.valueOf(multiplicationFactor), "../backoffice.consoleapp/src/main/resources/ecafeteria.properties");
    }

}
