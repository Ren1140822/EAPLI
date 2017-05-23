package eapli.ecafeteria;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * the application settings.
 *
 * @author Paulo Gandra Sousa
 */
public class AppSettings {

    public final static String USER_ALERT_LIMITS = "userAlertLimits";
    private final static String PROPERTIES_RESOURCE = "ecafeteria.properties";
    private final static String REPOSITORY_FACTORY_KEY = "persistence.repositoryFactory";
    private final static String UI_MENU_LAYOUT_KEY = "ui.menu.layout";
    private final static String PERSISTENCE_UNIT_KEY = "persistence.persistenceUnit";
    private final static String DOMAIN_MECANOGRAPHIC_NUMBER_KEY_ISEP = "domain.mecanographicNumberStrategy.ISEP";
    private final static String DOMAIN_MECANOGRAPHIC_NUMBER_KEY_HSJ = "domain.mecanographicNumberStrategy.HSJ";
    private final Properties applicationProperties = new Properties();

    public AppSettings() {
        loadProperties();
    }

    private void loadProperties() {
        InputStream propertiesStream = null;
        try {
            propertiesStream = this.getClass().getClassLoader().getResourceAsStream(PROPERTIES_RESOURCE);
            if (propertiesStream != null) {
                this.applicationProperties.load(propertiesStream);
            } else {
                throw new FileNotFoundException(
                        "property file '" + PROPERTIES_RESOURCE + "' not found in the classpath");
            }
        } catch (final IOException exio) {
            setDefaultProperties();

            Logger.getLogger(AppSettings.class.getName()).log(Level.SEVERE, null, exio);
        } finally {
            if (propertiesStream != null) {
                try {
                    propertiesStream.close();
                } catch (final IOException ex) {
                    Logger.getLogger(AppSettings.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void setDefaultProperties() {
        this.applicationProperties.setProperty(REPOSITORY_FACTORY_KEY,
                "eapli.ecafeteria.persistence.jpa.JpaRepositoryFactory");
        this.applicationProperties.setProperty(UI_MENU_LAYOUT_KEY, "horizontal");
        this.applicationProperties.setProperty(PERSISTENCE_UNIT_KEY, "eapli.eCafeteriaPU");
        this.applicationProperties.setProperty(DOMAIN_MECANOGRAPHIC_NUMBER_KEY_ISEP, "eapli.ecafeteria.domain.cafeteria.MecanographicNumberInvernessSchoolCenter");
        this.applicationProperties.setProperty(DOMAIN_MECANOGRAPHIC_NUMBER_KEY_HSJ, "eapli.ecafeteria.domain.cafeteria.MecanographicNumberSpringfieldHospitalCenter");
        this.applicationProperties.setProperty(USER_ALERT_LIMITS, "5");
    }

    public Boolean isMenuLayoutHorizontal() {
        return "horizontal".equalsIgnoreCase(this.applicationProperties.getProperty(UI_MENU_LAYOUT_KEY));
    }

    public String getPersistenceUnitName() {
        return this.applicationProperties.getProperty(PERSISTENCE_UNIT_KEY);
    }

    public String getRepositoryFactory() {
        return this.applicationProperties.getProperty(REPOSITORY_FACTORY_KEY);
    }

    public String getMecanographicNumberValidation(String organicUnitAcronym) {
        return this.applicationProperties.getProperty(organicUnitAcronym);
    }

    public void changeUserAlertsLimit(int multiplicationFactor) {
        this.applicationProperties.setProperty(USER_ALERT_LIMITS, String.valueOf(multiplicationFactor));
    }
    
    public int getMultiplicationFactorForBalanceAlert() {
        int factor = 0;
        
        try {
            factor = Integer.parseInt(this.applicationProperties.getProperty(USER_ALERT_LIMITS));
        } catch(Exception ex) {
            Logger.getLogger(AppSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return factor;
    }

}
