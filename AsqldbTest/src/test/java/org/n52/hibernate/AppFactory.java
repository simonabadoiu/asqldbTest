package test.java.org.n52.hibernate;

import java.sql.Array;

import main.java.org.n52.sos.config.rasdaman.HibernateMArrayType;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import rasj.RasGMArray;
//import org.hibernate.service.ServiceRegistryBuilder;

public class AppFactory {
	private static ServiceRegistry serviceRegistry;

	private static final SessionFactory sessionFactory;
	static {
		try {
			Configuration configuration=new Configuration();
			
//			configuration.registerTypeOverride(new ArrayUserType(), new String[] { "array", Array.class.getName() });
			configuration.registerTypeOverride(new HibernateMArrayType(), new String[] { "array", Array.class.getName() });
//					.configure(); // configures settings from hibernate.cfg.xml
			configuration.configure();
			
			StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();

			serviceRegistryBuilder.applySettings(configuration.getProperties());

			serviceRegistry = serviceRegistryBuilder.build();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
