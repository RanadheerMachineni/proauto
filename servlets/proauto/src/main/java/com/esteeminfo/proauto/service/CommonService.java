package com.esteeminfo.proauto.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.esteeminfo.proauto.dto.CustomerDTO;
import com.esteeminfo.proauto.dto.MachineDTO;
import com.esteeminfo.proauto.dto.PoDTO;
import com.esteeminfo.proauto.entity.Customer;
import com.esteeminfo.proauto.entity.FilesUpload;
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

	PurchaseOrder registerPO(String create, String pid, String poNumber, String poVersion, String poDate, String vnoSender,
			String poSender, String poSenderDetails, String senderEmail, String senderPhone, String senderFax,
			String notes, String matNo, String matDesc, String unitPrice, String quantity, String discount,
			String value) throws ParseException;

}
