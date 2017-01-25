package com.esteeminfo.proauto.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.esteeminfo.proauto.entity.Customer;
import com.esteeminfo.proauto.entity.Department;
import com.esteeminfo.proauto.entity.Employee;
import com.esteeminfo.proauto.entity.Jobcard;
import com.esteeminfo.proauto.entity.JobcardTask;
import com.esteeminfo.proauto.entity.PurchaseOrder;
import com.esteeminfo.proauto.entity.Role;
import com.esteeminfo.proauto.entity.Section;
import com.esteeminfo.proauto.entity.Status;

@Repository("jobcardDao")
public class JobcardDaoImpl extends AbstractDao implements JobcardDao {

	final static Logger logger = Logger.getLogger(EmployeeDaoImpl.class);

	public static SimpleDateFormat ui_date_format =  new SimpleDateFormat("MM/dd/yyyy");
	
	public Jobcard findById(int id) {
		return entityManager.find(Jobcard.class, id);
	}

	public List<Jobcard> retrieveAllJobcards(String jobcardSearched) {
		String query = "SELECT j FROM Jobcard j";
		if (jobcardSearched != null && jobcardSearched.length() > 0) {
			query += " where j.jobcardName LIKE '" + jobcardSearched + "%'";
		}
		Query q = entityManager.createQuery(query);
		List<Jobcard> result = q.getResultList();
		return result;
	}

	public Jobcard registerJobcard(String create, String jobcardId, String name, String desc, Customer customer,
			PurchaseOrder purchaseOrder, String status, String createdBy, String jobStart, String jobEnd,
			String[] jobop, String[] notes, String[] assignee, String[] startTime, String[] endTime, String[] duration,
			String[] machine, String[] cost, String[] taskStatus) throws Exception {
		int jid = (jobcardId == null || jobcardId.length() == 0 ) ? 0:Integer.valueOf(jobcardId); 
		java.util.Date javaStartdate = ui_date_format.parse(jobStart) ;
		java.util.Date javaEnddate = ui_date_format.parse(jobEnd) ;
		Jobcard jobcard = null;
		if (create.equalsIgnoreCase("false") && jid > 0 ) {
			jobcard = findById(jid);
			System.out.println("updating existing jc ******** "+jobcard.getJobcardId());
			jobcard.getJobcardTasks().clear();
		}else{
			jobcard = new Jobcard();
		}
		jobcard.setJobcardName(name);
		jobcard.setJobcardDesc(desc);
		jobcard.setCustomer(customer);
		jobcard.setPurchaseOrder(purchaseOrder);
		jobcard.setStatusBean(entityManager.find(Status.class, Integer.valueOf(status)));
		jobcard.setCreatedBy(createdBy);
		jobcard.setCreateDate(javaStartdate);
		jobcard.setEndDate(javaEnddate);
		entityManager.persist(jobcard);
		Set<JobcardTask> jobcardTasks =  new HashSet<JobcardTask>();
		for(int i=0;i<jobop.length;i++){
			if(jobop[i]!=null && jobop[i].length()>0){
				JobcardTask jobcardTask = new JobcardTask();
				jobcardTask.setJoId(Integer.valueOf(jobop[i]));
				jobcardTask.setNotes(notes[i]);
				jobcardTask.setAssignee(assignee[i]);
				jobcardTask.setStartTime(startTime[i]);
				jobcardTask.setEndTime(endTime[i]);
				jobcardTask.setTimeTaken(duration[i]);
				if(machine[i]!=null && machine[i].length()>0){
					jobcardTask.setMachineId(Integer.valueOf(machine[i]));
				}
				jobcardTask.setCost(cost[i]);
				jobcardTask.setJobcard(jobcard);
				entityManager.persist(jobcardTask);
				jobcardTasks.add(jobcardTask);
			}
		}
		if (jobcard.getJobcardTasks()!=null) {
			jobcard.getJobcardTasks().addAll(jobcardTasks);
		}else{
			jobcard.setJobcardTasks(jobcardTasks);
		}
		entityManager.persist(jobcard);
		return jobcard;
	}
	
}
