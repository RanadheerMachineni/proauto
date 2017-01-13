package com.esteeminfo.proauto.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.esteeminfo.proauto.dao.CommonDAO;
import com.esteeminfo.proauto.dao.FileUploadDAO;
import com.esteeminfo.proauto.dto.CustomerDTO;
import com.esteeminfo.proauto.dto.JobOpDTO;
import com.esteeminfo.proauto.dto.MachineDTO;
import com.esteeminfo.proauto.dto.PoDTO;
import com.esteeminfo.proauto.entity.Contact;
import com.esteeminfo.proauto.entity.FilesUpload;
import com.esteeminfo.proauto.entity.JobOperation;
import com.esteeminfo.proauto.entity.Machine;
import com.esteeminfo.proauto.entity.PoTool;
import com.esteeminfo.proauto.entity.PurchaseOrder;

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
		
		if(purchaseOrder.getPoTools()!=null && purchaseOrder.getPoTools().size()>0){
			List<String> poTools =  new ArrayList<String>();
			for(PoTool poTool: purchaseOrder.getPoTools()){
				if(poTool!=null && poTool.getMatNo()!=null && poTool.getMatNo().length()>0){
					poTools.add(poTool.getMatNo()+"|"+poTool.getMatDesc()+"|"+poTool.getMatQuantiy()+"|"+poTool.getMatUnitprice()+"|"+poTool.getDiscount()+"|"+poTool.getMatValue());
				}
			}
			poDTO.setMaterial(poTools);
		}	
		
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

	public PurchaseOrder registerPO(String create, String pid, String poNumber, String poVersion, String poDate,
			String vnoSender, String poSender, String poSenderDetails, String senderEmail, String senderPhone,
			String senderFax, String notes, String totalValue, Map<String, List<String>> matMap, MultipartFile[] files, List<String> uploadedFilesTrimmed) throws ParseException {
		PurchaseOrder purchaseOrder = commonDAO.registerPO(create, pid, poNumber, poVersion, poDate,
				vnoSender, poSender, poSenderDetails,  senderEmail,  senderPhone,
				 senderFax,  notes,  totalValue,  matMap);
		
		Set<FilesUpload> filesUploads = new HashSet<FilesUpload>();
		if(files!=null){
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				if(file.getOriginalFilename()!=null && file.getOriginalFilename().length()>0){
					logger.info("uploading file "+file.getOriginalFilename());
					try {
						byte[] bytes = file.getBytes();
						FilesUpload filesUpload =  fileUploadDAO.saveFile(file.getOriginalFilename(), bytes);
						filesUploads.add(filesUpload);
						
					} catch (Exception e) {
					}	
				}
				
			}
		}
		Set<FilesUpload> existingFiles = purchaseOrder.getFilesUploads();
		Set<FilesUpload> existingFilesNew = new HashSet<FilesUpload>();
		if(existingFiles!=null){
			for(FilesUpload existingFile : existingFiles){
				if(uploadedFilesTrimmed.contains(existingFile.getFileName())){
					existingFilesNew.add(existingFile);
				}
			}
		}
		filesUploads.addAll(existingFilesNew);
		purchaseOrder = commonDAO.addFilesToPO(purchaseOrder.getPid(), filesUploads);
		return purchaseOrder;
	}
}
