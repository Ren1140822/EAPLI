package eapli.ecafeteria;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import eapli.ecafeteria.domain.users.Session;

/**
 * A "global" (singleton) class with the application settings.
 *
 * @author Paulo Gandra Sousa
 */
public class AppSettings {

	private final Properties	applicationProperties  = new Properties();
	// FIXME use lazy holder idiom
	private static AppSettings	theInstance;
	// private final static String PROPERTIES_RESOURCE =
	// "eapli/ecafeteria/ecafeteria.properties";
	private final static String	PROPERTIES_RESOURCE	   = "ecafeteria.properties";
	private final static String	REPOSITORY_FACTORY_KEY = "persistence.repositoryFactory";

	// TODO use lazy holder idiom
	public static AppSettings instance() {
		if (theInstance == null) {
			theInstance = new AppSettings();
		}
		return theInstance;
	}

	private AppSettings() {
		loadProperties();
	}

	private void loadProperties() {
		InputStream propertiesStream = null;
		try {
			// propertiesStream = new FileInputStream(PROPERTIES_FILENAME);
			propertiesStream = AppSettings.class.getClassLoader().getResourceAsStream(PROPERTIES_RESOURCE);
			if (propertiesStream != null) {
				applicationProperties.load(propertiesStream);
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
		applicationProperties.setProperty(REPOSITORY_FACTORY_KEY,
		        "eapli.ecafeteria.persistence.jpa.JpaRepositoryFactory");
	}

	public String getRepositoryFactory() {
		return applicationProperties.getProperty(REPOSITORY_FACTORY_KEY);
	}

	//
	// session
	//

	private Session theSession = null;

	public void setSession(Session session) {
		theSession = session;
	}

	public void removeSession() {
		theSession = null;
	}

	public Session session() {
		return theSession;
	}
}
