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
import com.esteeminfo.proauto.entity.Machine;
import com.esteeminfo.proauto.entity.PurchaseOrder;
import com.esteeminfo.proauto.entity.Role;
import com.esteeminfo.proauto.entity.Section;
import com.esteeminfo.proauto.entity.Status;
import com.esteeminfo.proauto.service.EmployeeService;

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
			String[] jobop, String[] notes, String[] assignee, String[] programmer,  String[] duration,
			String[] machine, String[] cost, String[] taskStatus) throws Exception {
		int jid = (jobcardId == null || jobcardId.length() == 0 ) ? 0:Integer.valueOf(jobcardId); 
		java.util.Date javaStartdate = null;
		if(jobStart!=null && jobStart.length()>0)javaStartdate = ui_date_format.parse(jobStart) ;
		java.util.Date javaEnddate = null;
		if(jobEnd!=null && jobEnd.length()>0)javaEnddate = ui_date_format.parse(jobEnd) ;
		
		Jobcard jobcard = null;
		if (create.equalsIgnoreCase("false") && jid > 0 ) {
			jobcard = findById(jid);
			int oldStatus = jobcard.getStatusBean().getStatusId();
			if(oldStatus!=1 && Integer.valueOf(status)==1){
				throw new Exception("Can not move the status of Jobcard from "+jobcard.getStatusBean().getStatus()+" to New");
			}
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
		if(javaStartdate!=null){
			jobcard.setCreateDate(javaStartdate);
		}
		if(javaEnddate!=null){
			jobcard.setEndDate(javaEnddate);
		}
		entityManager.persist(jobcard);
		List<JobcardTask> jobcardTasks =  new ArrayList<JobcardTask>();
		for(int i=0;i<jobop.length;i++){
			if(jobop[i]!=null && jobop[i].length()>0){
				JobcardTask jobcardTask = new JobcardTask();
				jobcardTask.setJoId(Integer.valueOf(jobop[i]));
				jobcardTask.setNotes(notes[i]);
				String assigneeId = assignee[i];
				if(assigneeId!=null && assigneeId.length()>0){
					Employee assigneeOb = entityManager.find(Employee.class, Integer.valueOf(assigneeId));	
					jobcardTask.setAssignee(assigneeOb);
				}
				
				String programmerId = programmer[i];
				if(programmerId!=null && programmerId.length()>0){
					Employee programmerObj = entityManager.find(Employee.class, Integer.valueOf(programmerId));	
					jobcardTask.setProgrammer(programmerObj);
				}
				String tt = duration[i];
				jobcardTask.setTimeTaken(tt);
				if(machine[i]!=null && machine[i].length()>0){
					int mid = Integer.valueOf(machine[i]);
					Machine m = entityManager.find(Machine.class, Integer.valueOf(mid));
					jobcardTask.setMachine(m);
					int durInt=0;
					 try {
						 durInt =  Integer.parseInt(tt);
					 } catch (NumberFormatException nfe) {
					}
					 
					if(durInt > 0){
						jobcardTask.setCost(String.valueOf(Integer.valueOf(m.getMachineCost())*durInt));	
					}else{
						jobcardTask.setCost("0");	
					}
				}
				
				if(taskStatus[i]!=null && taskStatus[i].length()>0){
					jobcardTask.setStatusBean(entityManager.find(Status.class, Integer.valueOf(taskStatus[i])));
				}
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
