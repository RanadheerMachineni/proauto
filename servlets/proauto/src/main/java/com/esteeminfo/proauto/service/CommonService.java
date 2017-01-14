package com.esteeminfo.proauto.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.esteeminfo.proauto.dto.CustomerDTO;
import com.esteeminfo.proauto.dto.JobOpDTO;
import com.esteeminfo.proauto.dto.MachineDTO;
import com.esteeminfo.proauto.dto.PoDTO;
import com.esteeminfo.proauto.entity.Customer;
import com.esteeminfo.proauto.entity.FilesUpload;
import com.esteeminfo.proauto.entity.JobOperation;
import com.esteeminfo.proauto.entity.Machine;
import com.esteeminfo.proauto.entity.PurchaseOrder;

public interface CommonService {

	Machine findMachineById(int id);
	
	List<Machine> retrieveAllMachines(String machineSearched);

	Machine registerMachine(String create, String mid, String mName, String mCode, String mAxle, String mCost) throws Exception;
	
	MachineDTO converMachineToDto(Machine machine);

	PurchaseOrder findPOById(String poSelected);

	PoDTO converPoToDto(PurchaseOrder purchaseOrder);

	List<PurchaseOrder> retrieveAllPos(String poSearched);

	JobOpDTO converOperationToDto(JobOperation jobOperation);

	JobOperation findOperationById(Integer valueOf);

	List<JobOperation> retrieveAllOperations(String operationSearched);

	JobOperation registerJobOperation(String create, String oid, String oName, String oDescription);

	PurchaseOrder registerPO(String create, String pid, Customer customer, String poNumber, String poVersion, String poDate,
			String vnoSender, String poSender, String poSenderDetails, String senderEmail, String senderPhone,
			String senderFax, String notes, String totalValue, Map<String, List<String>> matMap, MultipartFile[] files, List<String> uploadedFilesTrimmed) throws Exception;

}
