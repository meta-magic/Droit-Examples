package com.droitstudio.examples;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.jdo.PersistenceManagerFactory;

import com.droitstudio.helper.PersistenceHelper;

/**
 * This class is defined to configure PersistenceManagerFactory 
 * 
 * @author Mahesh Pardeshi
 *
 */
public abstract class PMFConfig {

	private static PersistenceManagerFactory PERSISTENCE_MANAGER_FACTORY;

	public static PersistenceManagerFactory persistenceManagerFactory() {
		if (PERSISTENCE_MANAGER_FACTORY == null) {
			PERSISTENCE_MANAGER_FACTORY = PersistenceHelper.getPersistenceManagerFactory("OnlinestorePersistence");
		}
		return PERSISTENCE_MANAGER_FACTORY;
	}

	/** Method used to get persistence factory using properties file */
	@Deprecated
	public static PersistenceManagerFactory persistenceManagerFactory_deprecated() {
		if (PERSISTENCE_MANAGER_FACTORY == null) {
			new PMFBuilder().build("app.properties");
		}
		return PERSISTENCE_MANAGER_FACTORY;
	}

	/** Class to build Persistence Manager Factory */
	public static class PMFBuilder {

		/**
		 * Method to build Singleton PersistenceManagerFactory reading properties using fileName from classpath
		 * 
		 * @param fileName a properties file name
		 * @return Properties object loaded with properties
		 *      
		 * */
		public final Properties build(String fileName) {
			Properties properties = new Properties();
			InputStream inputStream = null;
			try {
				StringBuilder filePath = new StringBuilder("src/main/resources/").append(fileName);
				inputStream = new FileInputStream(filePath.toString());
				properties.load(inputStream);
				System.out.println(properties);
				PERSISTENCE_MANAGER_FACTORY = PersistenceHelper.getPersistenceManagerFactory(properties);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return properties;
		}
	}
}