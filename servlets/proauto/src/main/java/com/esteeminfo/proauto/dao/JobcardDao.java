package com.esteeminfo.proauto.dao;

import java.util.List;
import java.util.Set;

import com.esteeminfo.proauto.entity.Customer;
import com.esteeminfo.proauto.entity.Employee;
import com.esteeminfo.proauto.entity.FilesUpload;
import com.esteeminfo.proauto.entity.Jobcard;
import com.esteeminfo.proauto.entity.PurchaseOrder;

public interface JobcardDao {

	Jobcard findById(int id);

	List<Jobcard> retrieveAllJobcards(String jobcardSearched);

	Jobcard registerJobcard(String create, String jid, String name, String desc, Customer customer, PurchaseOrder purchaseOrder, String status, String createdBy, String jobStart, String jobEnd, String[] jobop, String[] notes, String[] assignee, String[] startTime, String[] endTime, String[] duration, String[] machine, String[] cost, String[] taskStatus) throws Exception;
	
}

