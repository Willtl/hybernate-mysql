package lu.uni.jpaproject;

import java.util.ArrayList;
import java.util.List;

import lu.uni.jpaproject.entities.Job;
import lu.uni.jpaproject.entities.Operation;
import lu.uni.jpaproject.util.JobManager;

/**
 * @author Willian Tessaro Lunardi
 *
 * @version 1.1
 */
public class App {

	public static void main(String[] args) {

		JobManager manager = new JobManager();

		Job job = new Job();
		Operation op = new Operation();
		op.setDescription("Operation 1!");
		job.setDescription("This is the 1st job.");
		job.setOperation(op);

		manager.saveJob(job);

		List<Job> userList = manager.getJobs();
		for (Job j : userList) {
			System.out.println(j.getId() + " " + j.getDescription());
		}
		System.out.println("Size: " + userList.size());

		userList = new ArrayList<Job>();
		userList = manager.getJobsCriteria();
		for (Job j : userList) {
			System.out.println(j.getId() + " " + j.getDescription());
		}
		System.out.println("Size: " + userList.size());

		Job j = manager.getJob(1);
		if (j != null){
			System.out.println(j.getDescription());
			manager.deleteJob(j);
		}
					
		manager.closeConnection();
	}
}
