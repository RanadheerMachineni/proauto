package com.esteeminfo.proauto.service;

import java.util.List;
import java.util.Map;

import com.esteeminfo.proauto.dto.JobcardDTO;
import com.esteeminfo.proauto.entity.Customer;
import com.esteeminfo.proauto.entity.Jobcard;
import com.esteeminfo.proauto.entity.PurchaseOrder;

public interface JobcardService {

	Jobcard findById(int id);
	
	List<Jobcard> retrieveAllJobcards(String jobcardSearched);

	Jobcard registerJobcard(String create, String jid, String name, String desc, Customer customer, PurchaseOrder purchaseOrder, String status, String createdBy, String jobStart, String jobEnd, String[] jobop, String[] notes, String[] assignee, String[] programmer, String[] duration, String[] machine, String[] cost, String[] taskStatus) throws Exception;
	
	JobcardDTO converJobcardToDto(Jobcard jobcard);

	List<JobcardDTO> retrieveAllJobcardDTOs(String jobcardSearched);

	JobcardDTO findDTOById(Integer valueOf);

	Map<String, String> findTasksByJob(String jobcard);

}
