package lu.uni.jpaproject;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import lu.uni.jpaproject.entities.Job;
import lu.uni.jpaproject.util.JPAManager;

/**
 * @author Willian Tessaro Lunardi
 *
 * @version 1.0
 */
public class App {
 
	public static void main(String[] args) {

		/*
		 * So now we need to make sure that JPA can store the Job in the
		 * database. For that we need to open a connection to the database.
		 *
		 * We need a manager to manage the database connection (EntityManager).
		 */

		JPAManager manager = new JPAManager();
		EntityManager em = manager.getEntityManagerFactory();

		// new object
		Job job = new Job();
		job.setDescription("This is the first job.");
		// persist object
		em.getTransaction().begin();
		em.persist(job);
		em.getTransaction().commit();
		
		// read the existing entries and write to console
		Query q = em.createQuery("SELECT u FROM Job u");
		List<Job> userList = q.getResultList();
		for (Job j : userList) {
			System.out.println(j.getId() + " " + j.getDescription());
		}
		System.out.println("Size: " + userList.size());

		// close transaction
		em.close();
	}
}
