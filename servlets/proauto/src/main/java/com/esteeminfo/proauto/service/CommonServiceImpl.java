package com.esteeminfo.proauto.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esteeminfo.proauto.dao.CommonDAO;
import com.esteeminfo.proauto.dto.CustomerDTO;
import com.esteeminfo.proauto.dto.JobOpDTO;
import com.esteeminfo.proauto.dto.MachineDTO;
import com.esteeminfo.proauto.dto.PoDTO;
import com.esteeminfo.proauto.entity.Contact;
import com.esteeminfo.proauto.entity.FilesUpload;
import com.esteeminfo.proauto.entity.JobOperation;
import com.esteeminfo.proauto.entity.Machine;
import com.esteeminfo.proauto.entity.PurchaseOrder;

@Service("commonService")
@Transactional
public class CommonServiceImpl implements CommonService {

	final static Logger logger = Logger.getLogger(CommonServiceImpl.class);

	public static SimpleDateFormat ui_date_format =  new SimpleDateFormat("MM/dd/yyyy");

	@Autowired(required=true)
	private CommonDAO commonDAO;
	
	public Machine findMachineById(int id) {
		return commonDAO.findMachineById(id);
	}

	public List<Machine> retrieveAllMachines(String machineSearched) {
		return commonDAO.retrieveAllMachines(machineSearched);
	}

	public MachineDTO converMachineToDto(Machine machine) {
		MachineDTO machineDTO =  new MachineDTO();
		machineDTO.setMachineId(String.valueOf(machine.getMachineId()));
		machineDTO.setName(machine.getMachineDesc());
		machineDTO.setCode(machine.getMachineCodeType());
		machineDTO.setAxle(machine.getMachineAxle());
		machineDTO.setCost(machine.getMachineCost());
		return machineDTO;
	}

	public Machine registerMachine(String create, String mid, String mName, String mCode, String mAxle, String mCost)
			throws Exception {
		return commonDAO.registerMachine(create, mid, mName, mCode, mAxle, mCost);
	}

	public PurchaseOrder findPOById(String valueOf) {
		return commonDAO.findPOById(valueOf);
	}

	public PoDTO converPoToDto(PurchaseOrder purchaseOrder) {
		PoDTO poDTO = new PoDTO();
		poDTO.setPid(purchaseOrder.getPid());
		poDTO.setPoId(purchaseOrder.getPoId());
		poDTO.setVersion(purchaseOrder.getPoVersion());
		poDTO.setVendor(purchaseOrder.getVnoSender());
		poDTO.setSender(purchaseOrder.getSenderContact());
		poDTO.setDate(ui_date_format.format(purchaseOrder.getPdate()));
		poDTO.setSenderDetails(purchaseOrder.getSenderDetails());
		poDTO.setSenderEmail(purchaseOrder.getSenderEmail());
		poDTO.setSenderPhone(purchaseOrder.getSenderPhone());
		poDTO.setSenderFax(purchaseOrder.getSenderFax());
		poDTO.setNotes(purchaseOrder.getNotes());
		poDTO.setMatNo(purchaseOrder.getMatNo());
		poDTO.setMatDesc(purchaseOrder.getMatDesc());
		poDTO.setUnitPrice(purchaseOrder.getMatUnitprice());
		poDTO.setQuantity(String.valueOf(purchaseOrder.getMatQuantiy()));
		poDTO.setDiscount(purchaseOrder.getDiscount());
		poDTO.setValue(purchaseOrder.getMatValue());
		return poDTO;
	}

	public List<PurchaseOrder> retrieveAllPos(String poSearched) {
		return commonDAO.retrieveAllPos(poSearched);
	}

	public PurchaseOrder registerPO(String create, String pid, String poNumber, String poVersion, String poDate, String vnoSender,
			String poSender, String poSenderDetails, String senderEmail, String senderPhone, String senderFax,
			String notes, String matNo, String matDesc, String unitPrice, String quantity, String discount,
			String value) throws ParseException {
		return commonDAO.registerPO(create, pid, poNumber, poVersion, poDate, vnoSender,
				poSender, poSenderDetails, senderEmail, senderPhone, senderFax,
				notes,  matNo,  matDesc,  unitPrice,  quantity,  discount,
				 value);

	}

	public JobOpDTO converOperationToDto(JobOperation jobOperation) {
		JobOpDTO jobOpDTO=  new JobOpDTO();
		jobOpDTO.setId(jobOperation.getJoId());
		jobOpDTO.setName(jobOperation.getJobName());
		jobOpDTO.setDesc(jobOperation.getJobDesc());
		return jobOpDTO;
	}

	public JobOperation findOperationById(Integer valueOf) {
		return commonDAO.findOperationById(valueOf);
	}

	public List<JobOperation> retrieveAllOperations(String operationSearched) {
		return commonDAO.retrieveAllOperations(operationSearched);
	}

	public JobOperation registerJobOperation(String create, String oid, String oName, String oDescription) {
		return commonDAO.registerOperation(create, oid, oName, oDescription);

	}
}
