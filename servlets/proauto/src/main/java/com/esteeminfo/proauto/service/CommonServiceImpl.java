package com.esteeminfo.proauto.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.esteeminfo.proauto.dao.CommonDAO;
import com.esteeminfo.proauto.dao.FileUploadDAO;
import com.esteeminfo.proauto.dto.JobOpDTO;
import com.esteeminfo.proauto.dto.MachineDTO;
import com.esteeminfo.proauto.dto.PoDTO;
import com.esteeminfo.proauto.dto.PurchaseDTO;
import com.esteeminfo.proauto.dto.PurchaseHistoryDTO;
import com.esteeminfo.proauto.entity.Customer;
import com.esteeminfo.proauto.entity.JobOperation;
import com.esteeminfo.proauto.entity.Machine;
import com.esteeminfo.proauto.entity.PoTool;
import com.esteeminfo.proauto.entity.Purchase;
import com.esteeminfo.proauto.entity.PurchaseHistory;
import com.esteeminfo.proauto.entity.PurchaseOrder;
import com.esteeminfo.proauto.entity.Status;

@Service("commonService")
@Transactional
public class CommonServiceImpl implements CommonService {

	final static Logger logger = Logger.getLogger(CommonServiceImpl.class);

	public static SimpleDateFormat ui_date_format =  new SimpleDateFormat("MM/dd/yyyy");

	@Autowired(required=true)
	private CommonDAO commonDAO;
	
	@Autowired(required=true)
	private FileUploadDAO fileUploadDAO ;
	
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
		return commonDAO.findPOById(Integer.valueOf(valueOf));
	}

	public PoDTO converPoToDto(PurchaseOrder purchaseOrder, List<String> fileNames) {
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
		poDTO.setTotalValue(purchaseOrder.getTotalValue());

		if(purchaseOrder.getCustomer()!=null){
			poDTO.setCustomer(String.valueOf(purchaseOrder.getCustomer().getCustomerId()));
		}
		if(purchaseOrder.getPoTools()!=null && purchaseOrder.getPoTools().size()>0){
			List<String> poTools =  new ArrayList<String>();
			for(PoTool poTool: purchaseOrder.getPoTools()){
				if(poTool!=null && poTool.getMatNo()!=null && poTool.getMatNo().length()>0){
					poTools.add(poTool.getMatNo()+"|"+poTool.getMatDesc()+"|"+poTool.getMatQuantiy()+"|"+poTool.getMatUnitprice()+"|"+poTool.getDiscount()+"|"+poTool.getMatValue());
				}
			}
			poDTO.setMaterial(poTools);
		}	
		
		poDTO.setFiles(fileNames);
		return poDTO;
	}

	public List<PurchaseOrder> retrieveAllPos(String poSearched) {
		return commonDAO.retrieveAllPos(poSearched);
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
	public Map<String, String> getJobOperations() {
		List<JobOperation> list= commonDAO.getJobOperations();
		Map<String, String> operations = new HashMap<String, String>();
		for(JobOperation jobOp : list){
			operations.put(String.valueOf(jobOp.getJoId()), jobOp.getJobName());
		}
		return operations;
	}

	public Map<String, String> getStatuses() {
		List<Status> list= commonDAO.getStatuses();
		Map<String, String> operations = new HashMap<String, String>();
		for(Status jobOp : list){
			operations.put(String.valueOf(jobOp.getStatusId()), jobOp.getStatus());
		}
		return operations;
	}

	public Map<String, String> getMachines() {
		List<Machine> list= commonDAO.getMachines();
		Map<String, String> operations = new HashMap<String, String>();
		for(Machine jobOp : list){
			operations.put(String.valueOf(jobOp.getMachineId()), jobOp.getMachineDesc());
		}
		return operations;
	}

	public Map<String,String> findPOByCustId(String customer) {
		Map<String,String> poMap =  new HashMap<String, String>();
		List<PurchaseOrder> pos = commonDAO.findPosByCustomer(customer);
		for (PurchaseOrder purchaseOrder : pos) {
			poMap.put(String.valueOf(purchaseOrder.getPid()), purchaseOrder.getPoId());
		}
		return poMap;
	}

	public PoDTO findPoDTOById(int id) {
		PurchaseOrder purchaseOrder = findPOById(String.valueOf(id));
		List<String> fileNames = commonDAO.findPOFileNames(id);
		if(purchaseOrder!=null) return converPoToDto(purchaseOrder,fileNames);
		return null;
	}

	public List<PoDTO> retrieveAllPoDTOs(String poSearched) {
		List<PoDTO> poDTOList = new ArrayList<PoDTO>();
		List<PurchaseOrder> poList = retrieveAllPos(poSearched);
		for(PurchaseOrder eachPO : poList){
			PoDTO eachPoDTO = convertPoToMiniDto(eachPO);
			poDTOList.add(eachPoDTO);
		}
		return poDTOList;
	}

	private PoDTO convertPoToMiniDto(PurchaseOrder purchaseOrder) {

		PoDTO poDTO = new PoDTO();
		poDTO.setPid(purchaseOrder.getPid());
		poDTO.setPoId(purchaseOrder.getPoId());
		poDTO.setVersion(purchaseOrder.getPoVersion());
		poDTO.setSender(purchaseOrder.getSenderContact());
		poDTO.setDate(ui_date_format.format(purchaseOrder.getPdate()));
		poDTO.setSenderDetails(purchaseOrder.getSenderDetails());
		return poDTO;
	
	}

	public PurchaseOrder registerPO(String create, String pid, Customer customer, String poNumber, String poVersion,
			String poDate, String vnoSender, String poSender, String poSenderDetails, String senderEmail,
			String senderPhone, String senderFax, String notes, String totalValue, Map<String, List<String>> matMap,
			MultipartFile[] files, String removedFiles) throws Exception {
		PurchaseOrder purchaseOrder = commonDAO.registerPO(create, pid, customer, poNumber, poVersion, poDate,
				vnoSender, poSender, poSenderDetails,  senderEmail,  senderPhone,
				 senderFax,  notes,  totalValue,  matMap, files, removedFiles);
		return purchaseOrder;
	}

	public byte[] findPOFile(Integer pid, String fileName) {
		byte[] fileData = commonDAO.findPoFileData(pid, fileName);
		return fileData;
	}

	public PurchaseDTO findPurchaseDTOById(Integer valueOf) {
		Purchase purchase = findPurchaseById(String.valueOf(valueOf));
		if(purchase!=null) return converPurchaseToDto(purchase);
		return null;
	}
	
	private PurchaseDTO converPurchaseToDto(Purchase purchase) {
		PurchaseDTO purchaseDTO = new PurchaseDTO();
		purchaseDTO.setId(purchase.getParticularId());
		purchaseDTO.setParticular(purchase.getParticular());
		purchaseDTO.setCode(purchase.getCode());
		purchaseDTO.setDesciption(purchase.getDesciption());
		purchaseDTO.setMake(purchase.getMake());
		purchaseDTO.setAuthouredby(purchase.getAuthouredby());
		purchaseDTO.setQuantity(String.valueOf(purchase.getQuantity()));
		purchaseDTO.setRepository(String.valueOf(purchase.getRepository()));
		purchaseDTO.setTooltypeId(String.valueOf(purchase.getTooltypeId()));
		purchaseDTO.setUnit(purchase.getUnit());
		purchaseDTO.setDoc(ui_date_format.format(purchase.getDoc()));
		purchaseDTO.setDou(ui_date_format.format(purchase.getDou()));
		List<PurchaseHistoryDTO> historyDTOs = new ArrayList<PurchaseHistoryDTO>();
		if(purchase.getPurchaseHistories()!=null && purchase.getPurchaseHistories().size()>0){
			System.out.println("Size of histories = "+purchase.getPurchaseHistories().size());
			for(PurchaseHistory purchaseHistory : purchase.getPurchaseHistories()){
				PurchaseHistoryDTO purchaseHistoryDTO = new PurchaseHistoryDTO();
				purchaseHistoryDTO.setAuthouredby(purchaseHistory.getAuthouredby());
				purchaseHistoryDTO.setDate(ui_date_format.format(purchaseHistory.getAdddate()));
				purchaseHistoryDTO.setQuantity(String.valueOf(purchaseHistory.getQuantity()));
				historyDTOs.add(purchaseHistoryDTO);
			}
		}
		purchaseDTO.setPurchaseHistory(historyDTOs);
		return purchaseDTO;
	}

	public Purchase findPurchaseById(String valueOf) {
		return commonDAO.findPurchaseById(Integer.valueOf(valueOf));
	}

	public List<PurchaseDTO> retrieveAllPurchaseDTO(String purchaseSearched) {
		List<PurchaseDTO> purchaseDTOList = new ArrayList<PurchaseDTO>();
		List<Purchase> purchaseList = commonDAO.retrieveAllPurchase(purchaseSearched);
		for(Purchase purchase : purchaseList){
			PurchaseDTO eachPurchaseDTO = convertPurchaseToMiniDto(purchase);
			purchaseDTOList.add(eachPurchaseDTO);
		}
		return purchaseDTOList;
	}

	private PurchaseDTO convertPurchaseToMiniDto(Purchase purchase) {
		PurchaseDTO purchaseDTO = new PurchaseDTO();
		purchaseDTO.setId(purchase.getParticularId());
		purchaseDTO.setParticular(purchase.getParticular());
		purchaseDTO.setCode(purchase.getCode());
		purchaseDTO.setMake(purchase.getMake());
		purchaseDTO.setRepository(String.valueOf(purchase.getRepository()));
		return purchaseDTO;
	}

	public Purchase registerPurchase(String create, String parid, String particular, String code, String make,
			String unit, String desc, String type, String authouredby, String additems) throws Exception {
		Purchase purchase = commonDAO.registerPurchase(create, parid, particular, code, make,
				unit, desc, type, authouredby,  additems);
		return purchase;
				
	}

}
