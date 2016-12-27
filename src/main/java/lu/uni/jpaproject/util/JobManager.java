package lu.uni.jpaproject.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import lu.uni.jpaproject.entities.Job;

public class JobManager {

	private static final String PERSISTENCE_UNIT_NAME = "persistenceUnit";
	private static EntityManagerFactory factory;
	private static EntityManager entityManager;

	public JobManager() {
		super();

		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		entityManager = factory.createEntityManager();
	}

	public EntityManager getEntityManagerFactory() {
		return factory.createEntityManager();
	}

	public void saveJob(Job job) {
		entityManager.getTransaction().begin();
		entityManager.persist(job);
		entityManager.getTransaction().commit();
	}

	public void deleteJob(Job job) {
		entityManager.getTransaction().begin();
		entityManager.remove(job);
		entityManager.getTransaction().commit();
	} 

	public List<Job> getJobs() {
		return entityManager.createQuery("SELECT u FROM Job u").getResultList();
	}
	
	public List<Job> getJobsCriteria() {
		CriteriaBuilder critBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Job> critQuery = critBuilder.createQuery(Job.class);
		critQuery.from(Job.class);
		return entityManager.createQuery(critQuery).getResultList();
	}

	// TODO
	// replicate try-catch to other methods
	public Job getJob(int id) {
		try {
			Job j = entityManager.find(Job.class, id);
			return j;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Job getJobByQuery(String query) {
		return (Job) entityManager.createQuery(query).getResultList();
	} 
	
	public void closeConnection() {
		entityManager.close();
	}
}
