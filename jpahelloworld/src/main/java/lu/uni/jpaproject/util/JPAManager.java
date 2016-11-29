package lu.uni.jpaproject.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAManager {

	private static final String PERSISTENCE_UNIT_NAME = "persistenceUnit";
	private static EntityManagerFactory factory;

	public JPAManager() {
		super();

		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
	}
	
	public EntityManager getEntityManagerFactory(){
		return factory.createEntityManager();
	}
}
