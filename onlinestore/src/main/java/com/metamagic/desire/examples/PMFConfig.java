/**
 * Class is defined to configure PersistenceManagerFactory 
 */
package com.metamagic.desire.examples;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/**
 * @author Mahesh Pardeshi
 *
 */
public abstract class PMFConfig {

	private static final PersistenceManagerFactory PERSISTENCE_MANAGER_FACTORY = JDOHelper.getPersistenceManagerFactory("PersistenceUnit");

	public static PersistenceManagerFactory persistenceManagerFactory() {
		return PERSISTENCE_MANAGER_FACTORY;
	}
}
