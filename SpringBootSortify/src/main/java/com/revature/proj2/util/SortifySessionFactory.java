package com.revature.proj2.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.revature.proj2.model.Playlist;
import com.revature.proj2.model.User;

/**
 * Revature Proj2: Sortify
 * Package: Util
 * SortifySessionFactory.java
 * Utilities
 * Purpose: Declares Data Access Object
 * 
 * @author Neil Gorham
 * @version 1.0.0 10/02/2019
 */

public class SortifySessionFactory {
	//Singleton factory
	private static SessionFactory sessionFactory;
	//Session Getter
	public static Session getSession() {
		if(sessionFactory == null) {
			Utilities.setupDBConfig();
			Configuration config = new Configuration();
			config.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
			config.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
			config.setProperty("hibernate.connection.url", Utilities.getDBConfig("url"));
			config.setProperty("hibernate.connection.username", Utilities.getDBConfig("username"));
			config.setProperty("hibernate.connection.password", Utilities.getDBConfig("password"));
			config.setProperty("hibernate.connection.pool_size", "5");
			config.setProperty("hibernate.current_session_context_class", "thread");
			config.setProperty("hibernate.hbm2ddl.auto", "validate");
			config.addClass(User.class);
			config.addClass(Playlist.class);
			//config.configure();
			sessionFactory = config.buildSessionFactory();
		}
		return sessionFactory.getCurrentSession();
	}
}
