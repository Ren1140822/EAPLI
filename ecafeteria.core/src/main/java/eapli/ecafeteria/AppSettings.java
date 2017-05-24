package eapli.ecafeteria;

import eapli.ecafeteria.domain.authz.ActionRight;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * the application settings.
 *
 * @author Paulo Gandra Sousa
 */
public class AppSettings {

    public final static String USER_ALERT_LIMITS = "userAlertLimits";
    public final static String KITCHEN_RED_ALERT = "kitchenRedAlert";
    public final static String KITCHEN_YELLOW_ALERT = "kitchenYellowAlert";
    private final static String PROPERTIES_RESOURCE = "ecafeteria.properties";
    private final static String REPOSITORY_FACTORY_KEY = "persistence.repositoryFactory";
    private final static String UI_MENU_LAYOUT_KEY = "ui.menu.layout";
    private final static String PERSISTENCE_UNIT_KEY = "persistence.persistenceUnit";
    private final static String DOMAIN_MECANOGRAPHIC_NUMBER_KEY_ISEP = "domain.mecanographicNumberStrategy.ISEP";
    private final static String DOMAIN_MECANOGRAPHIC_NUMBER_KEY_HSJ = "domain.mecanographicNumberStrategy.HSJ";
    private final Properties applicationProperties = new Properties();
    
    private final static int KITCHEN_YELLOW_ALERT_LIMIT=75;
    private final static int KITCHEN_RED_ALERT_LIMIT=90;
    public enum ALERT_TYPE{
        KITCHEN_RED, KITCHEN_YELLOW
    }

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
        this.applicationProperties.setProperty(KITCHEN_RED_ALERT, Integer.toString(KITCHEN_RED_ALERT_LIMIT));
        this.applicationProperties.setProperty(KITCHEN_YELLOW_ALERT, Integer.toString(KITCHEN_YELLOW_ALERT_LIMIT));
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
    
    public Iterable<String> getKitchenAlertsTypes(){
        Set<String> list= new HashSet<>();
        for(ALERT_TYPE type: ALERT_TYPE.values()){
             list.add(type.toString());
        }
        return list;
    }
    
    public void changeKitchenYellowAlertLimit(int value) {
        
        Application.ensurePermissionOfLoggedInUser(ActionRight.ADMINISTER);
        this.applicationProperties.setProperty(KITCHEN_YELLOW_ALERT, Integer.toString(value));
    }
    
    public void changeKitchenRedAlertLimit(int value) {
        Application.ensurePermissionOfLoggedInUser(ActionRight.ADMINISTER);
        this.applicationProperties.setProperty(KITCHEN_RED_ALERT, Integer.toString(value));
    }
    
    public int getKitchenAlertLimit(ALERT_TYPE type) {
        int percentage = 0;
        
        try {
            if(type.equals(ALERT_TYPE.KITCHEN_RED)){
                percentage = Integer.parseInt(this.applicationProperties.getProperty(KITCHEN_RED_ALERT));
            }
            if(type.equals(ALERT_TYPE.KITCHEN_YELLOW)){
                percentage = Integer.parseInt(this.applicationProperties.getProperty(KITCHEN_YELLOW_ALERT));       
            }
         } catch(NumberFormatException ex) {
            Logger.getLogger(AppSettings.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        return percentage;
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
