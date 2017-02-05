package com.esteeminfo.proauto.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.esteeminfo.proauto.dao.EmployeeDao;
import com.esteeminfo.proauto.dao.FileUploadDAO;
import com.esteeminfo.proauto.dao.JobcardDao;
import com.esteeminfo.proauto.dto.EmployeeDTO;
import com.esteeminfo.proauto.dto.JobcardDTO;
import com.esteeminfo.proauto.dto.JobcardTaskDTO;
import com.esteeminfo.proauto.entity.Contact;
import com.esteeminfo.proauto.entity.Customer;
import com.esteeminfo.proauto.entity.Employee;
import com.esteeminfo.proauto.entity.Jobcard;
import com.esteeminfo.proauto.entity.JobcardTask;
import com.esteeminfo.proauto.entity.PurchaseOrder;
import com.esteeminfo.proauto.entity.Role;

@Service("jobcardService")
@Transactional
public class JobcardServiceImpl implements JobcardService {

	final static Logger logger = Logger.getLogger(JobcardServiceImpl.class);

	public static SimpleDateFormat ui_date_format =  new SimpleDateFormat("MM/dd/yyyy");
	
	@Autowired(required=true)
	private JobcardDao jobcardDao ;

	public Jobcard findById(int id) {
		return jobcardDao.findById(id);
	}

	public List<Jobcard> retrieveAllJobcards(String jobcardSearched) {
		return jobcardDao.retrieveAllJobcards(jobcardSearched);
	}


	public JobcardDTO converJobcardToDto(Jobcard jobcard) {
		JobcardDTO jobcardDTO =  new JobcardDTO();
		jobcardDTO.setId(jobcard.getJobcardId());
		jobcardDTO.setName(jobcard.getJobcardName());
		jobcardDTO.setDesc(jobcard.getJobcardDesc());
		if(jobcard.getCustomer()!=null)	jobcardDTO.setCustomer(String.valueOf(jobcard.getCustomer().getCustomerId()));
		if(jobcard.getPurchaseOrder()!=null){
			jobcardDTO.setPo(String.valueOf(jobcard.getPurchaseOrder().getPid()));
		}
		if(jobcard.getJobcardTasks()!=null && jobcard.getJobcardTasks().size()>0){
			List<JobcardTaskDTO> tasks =  new ArrayList<JobcardTaskDTO>();
			for(JobcardTask jobcardTask : jobcard.getJobcardTasks()){
				if(jobcardTask!=null && jobcardTask.getJoId()>0 ){
					JobcardTaskDTO jobcardTaskDTO = new JobcardTaskDTO();
					jobcardTaskDTO.setJoId(String.valueOf(jobcardTask.getJoId()));
					jobcardTaskDTO.setNotes(jobcardTask.getNotes());
					jobcardTaskDTO.setAssignee(jobcardTask.getAssignee());
					jobcardTaskDTO.setStartTime(jobcardTask.getStartTime());
					jobcardTaskDTO.setEndTime(jobcardTask.getEndTime());
					jobcardTaskDTO.setTimeTaken(jobcardTask.getTimeTaken());
					jobcardTaskDTO.setMachineId(String.valueOf(jobcardTask.getMachineId()));
					jobcardTaskDTO.setCost(jobcardTask.getCost());
					jobcardTaskDTO.setStatus(jobcardTask.getStatusBean()!=null ? String.valueOf(jobcardTask.getStatusBean().getStatusId()) : "");
					tasks.add(jobcardTaskDTO);
				}
			}
			jobcardDTO.setTasks(tasks);
			
		}
		if(jobcard.getStatusBean()!=null)jobcardDTO.setState(String.valueOf(jobcard.getStatusBean().getStatusId()));
		jobcardDTO.setCreatedBy(jobcard.getCreatedBy());
		if(jobcard.getCreateDate()!=null)jobcardDTO.setJobStart(ui_date_format.format(jobcard.getCreateDate()));
		if(jobcard.getEndDate()!=null)jobcardDTO.setJobEnd(ui_date_format.format(jobcard.getEndDate()));
		return jobcardDTO;
	}

	public Jobcard registerJobcard(String create, String jid, String name, String desc, Customer customer,
			PurchaseOrder purchaseOrder, String status, String createdBy, String jobStart, String jobEnd,
			String[] jobop, String[] notes, String[] assignee, String[] startTime, String[] endTime, String[] duration,
			String[] machine, String[] cost, String[] taskStatus) throws Exception {
		return jobcardDao.registerJobcard(create, jid, name, desc, customer,
				purchaseOrder, status, createdBy,  jobStart,  jobEnd,
				 jobop,  notes,  assignee,  startTime,  endTime, duration,
				 machine,  cost, taskStatus);
	}

	public List<JobcardDTO> retrieveAllJobcardDTOs(String jobcardSearched) {
		List<JobcardDTO> jobcardDTOs = new ArrayList<JobcardDTO>();
		List<Jobcard> jobcards = retrieveAllJobcards(jobcardSearched);
		for(Jobcard jobcard : jobcards){
			JobcardDTO eachDTO = convertJcToMiniDto(jobcard);
			jobcardDTOs.add(eachDTO);
		}
		return jobcardDTOs;
	}

	private JobcardDTO convertJcToMiniDto(Jobcard jobcard) {

		JobcardDTO jobcardDTO =  new JobcardDTO();
		jobcardDTO.setId(jobcard.getJobcardId());
		jobcardDTO.setName(jobcard.getJobcardName());
		jobcardDTO.setDesc(jobcard.getJobcardDesc());
		if(jobcard.getCustomer()!=null)	jobcardDTO.setCustomer(String.valueOf(jobcard.getCustomer().getCustomerId()));
		if(jobcard.getPurchaseOrder()!=null){
			jobcardDTO.setPo(String.valueOf(jobcard.getPurchaseOrder().getPid()));
		}
		return jobcardDTO;
	
	}

	public JobcardDTO findDTOById(Integer valueOf) {
		Jobcard jobcard = findById(valueOf);
		if(jobcard!=null) return converJobcardToDto(jobcard);
		return null;
	}
	
	
}
