package com.esteeminfo.proauto.dao;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.esteeminfo.proauto.entity.Customer;
import com.esteeminfo.proauto.entity.FilesUpload;
import com.esteeminfo.proauto.entity.JobOperation;
import com.esteeminfo.proauto.entity.Machine;
import com.esteeminfo.proauto.entity.PoTool;
import com.esteeminfo.proauto.entity.PurchaseOrder;

@Repository("commonDAO")
public class CommonDAO extends AbstractDao{

	public static SimpleDateFormat ui_date_format =  new SimpleDateFormat("MM/dd/yyyy");

	public void loadRoleMap(Map<String, String> roleMap) {
			
	}
	
	public Machine findMachineById(int id){
		EntityManager entityManager = getEntityManager();
		Query q = entityManager.createQuery( "select e from Machine e where e.machineId=:eid");
		q.setParameter("eid", id);
		List<Machine> result = q.getResultList();
		if(result == null || result.size() ==0){
			return null;
		}
		Machine e = result.get(0);
		return e;
	}
	
	public List<Machine> retrieveAllMachines(String machineSearched){
		EntityManager entityManager = getEntityManager();
		String query = "select e from Machine e";
		if (machineSearched != null && machineSearched.length() > 0) {
			query += " where e.machineDesc LIKE '" + machineSearched + "%'";
		}
		Query q = entityManager.createQuery(query);
		List<Machine> result = q.getResultList();
		return result;
	}

	public Machine registerMachine(String create, String mid, String mName, String mCode, String mAxle, String mCost) throws Exception{
		int machineId = (mid == null || mid.length() == 0 ) ? 0:Integer.valueOf(mid); 


		if (create.equalsIgnoreCase("false") && machineId > 0 ) {
			EntityManager entityManager = getEntityManager();
			Machine existingMachine = null;
			Query q = entityManager.createQuery( "select e from Machine e where e.machineId=:eid");
			q.setParameter("eid", machineId);
			List<Machine> result = q.getResultList();
			if(result != null || result.size() > 0){
				existingMachine = result.get(0);
			}

			existingMachine.setMachineDesc(mName);
			existingMachine.setMachineCodeType(mCode);
			existingMachine.setMachineAxle(mAxle);
			existingMachine.setMachineCost(mCost);
			entityManager.persist(existingMachine);
			return existingMachine;
		}else{
			EntityManager entityManager = getEntityManager();
			Machine machineCreated =  new Machine();
			machineCreated.setMachineDesc(mName);
			machineCreated.setMachineCodeType(mCode);
			machineCreated.setMachineAxle(mAxle);
			machineCreated.setMachineCost(mCost);
			entityManager.persist(machineCreated);
			return machineCreated;
		}
	}

	public PurchaseOrder findPOById(int valueOf) {
		EntityManager entityManager = getEntityManager();
		Query q = entityManager.createQuery( "select e from PurchaseOrder e where e.pid=:pid");
		q.setParameter("pid", Integer.valueOf(valueOf));
		List<PurchaseOrder> result = q.getResultList();
		if(result == null || result.size() ==0){
			return null;
		}
		PurchaseOrder e = result.get(0);
		return e;
	}

	public List<PurchaseOrder> retrieveAllPos(String poSearched) {
		EntityManager entityManager = getEntityManager();
		String query = "select e from PurchaseOrder e";
		if (poSearched != null && poSearched.length() > 0) {
			query += " where e.poId LIKE '" + poSearched + "%'";
		}
		Query q = entityManager.createQuery(query);
		List<PurchaseOrder> result = q.getResultList();
		return result;
	}

	private PurchaseOrder findPOByNumber(String poNumber) {
		EntityManager entityManager = getEntityManager();
		Query q = entityManager.createQuery( "select e from PurchaseOrder e where e.poId=:userId");
		q.setParameter("userId", poNumber);
		List<PurchaseOrder> result = q.getResultList();
		if(result == null || result.size() ==0){
			return null;
		}
		return result.get(0);
	}


	public JobOperation findOperationById(Integer valueOf) {
		EntityManager entityManager = getEntityManager();
		Query q = entityManager.createQuery( "select e from JobOperation e where e.joId=:eid");
		q.setParameter("eid", valueOf);
		List<JobOperation> result = q.getResultList();
		if(result == null || result.size() ==0){
			return null;
		}
		JobOperation e = result.get(0);
		return e;
	}

	public List<JobOperation> retrieveAllOperations(String operationSearched) {
		EntityManager entityManager = getEntityManager();
		String query = "select e from JobOperation e";
		if (operationSearched != null && operationSearched.length() > 0) {
			query += " where e.jobName LIKE '" + operationSearched + "%'";
		}
		Query q = entityManager.createQuery(query);
		List<JobOperation> result = q.getResultList();
		return result;
	}

	public JobOperation registerOperation(String create, String oid, String oName, String oDescription) {
		int joid = (oid == null || oid.length() == 0 ) ? 0:Integer.valueOf(oid); 

		if (create.equalsIgnoreCase("false") && joid > 0 ) {
			EntityManager entityManager = getEntityManager();
			JobOperation existingMachine = null;
			Query q = entityManager.createQuery( "select e from JobOperation e where e.joId=:eid");
			q.setParameter("eid", joid);
			List<JobOperation> result = q.getResultList();
			if(result != null || result.size() > 0){
				existingMachine = result.get(0);
			}
			existingMachine.setJobName(oName);
			existingMachine.setJobDesc(oDescription);
			entityManager.persist(existingMachine);
			return existingMachine;
		}else{
			EntityManager entityManager = getEntityManager();
			JobOperation machineCreated =  new JobOperation();
			machineCreated.setJobName(oName);
			machineCreated.setJobDesc(oDescription);
			entityManager.persist(machineCreated);
			return machineCreated;
		}	
		
	}

	public PurchaseOrder registerPO(String create, String pid, Customer customer, String poNumber, String poVersion, String poDate,
			String vnoSender, String poSender, String poSenderDetails, String senderEmail, String senderPhone,
			String senderFax, String notes, String totalValue, Map<String, List<String>> matMap) throws Exception {
		int purchaseid = (pid == null || pid.length() == 0 ) ? 0:Integer.valueOf(pid); 
		
		if(poNumber!=null && poNumber.length()>0){
			PurchaseOrder existingPOByNumber =  findPOByNumber(poNumber);
			if (existingPOByNumber!=null && (purchaseid==0 || (existingPOByNumber.getPid() != purchaseid))) {
				throw new Exception("PurchaseOrder with given PO number already exist. Please select other PO number");
			}
		}
		java.util.Date javaDatePoDate = null;
		if(poDate!=null){
			javaDatePoDate = ui_date_format.parse(poDate) ;
		}
		
		if (create.equalsIgnoreCase("false") && purchaseid > 0) {
			EntityManager entityManager = getEntityManager();
			PurchaseOrder existingPO = null;
			Query q = entityManager.createQuery( "select e from PurchaseOrder e where e.pid=:eid");
			q.setParameter("eid", purchaseid);
			List<PurchaseOrder> result = q.getResultList();
			if(result != null || result.size() > 0){
				existingPO = result.get(0);
			}
			existingPO.setPoId(poNumber);
			existingPO.setPoVersion(poVersion);
			existingPO.setPdate(javaDatePoDate);
			existingPO.setVnoSender(vnoSender);
			existingPO.setSenderContact(poSender);
			existingPO.setSenderDetails(poSenderDetails);
			existingPO.setSenderEmail(senderEmail);
			existingPO.setSenderPhone(senderPhone);
			existingPO.setSenderFax(senderFax);
			existingPO.setNotes(notes);
			existingPO.setCustomer(customer);
			entityManager.persist(existingPO);
			
			Set<PoTool> poList = new HashSet<PoTool>();
			for (Entry<String, List<String>> eachEntry : matMap.entrySet()) {
				PoTool poTool = new PoTool();
				poTool.setPurchaseOrder(existingPO);
				poTool.setMatNo(eachEntry.getKey());
				poTool.setMatDesc(eachEntry.getValue().get(0));
				poTool.setMatUnitprice(eachEntry.getValue().get(1));
				poTool.setMatQuantiy(Integer.valueOf(eachEntry.getValue().get(2)));
				poTool.setDiscount(eachEntry.getValue().get(3));
				poTool.setMatValue(eachEntry.getValue().get(4));
				entityManager.persist(poTool);
				poList.add(poTool);
			}
			
			existingPO.setPoTools(poList);
			entityManager.merge(existingPO);
			return existingPO;
		}else{
			EntityManager entityManager = getEntityManager();
			PurchaseOrder poCreated =  new PurchaseOrder();
			poCreated.setPoId(poNumber);
			poCreated.setPoVersion(poVersion);
			poCreated.setPdate(javaDatePoDate);
			poCreated.setVnoSender(vnoSender);
			poCreated.setSenderContact(poSender);
			poCreated.setSenderDetails(poSenderDetails);
			poCreated.setSenderEmail(senderEmail);
			poCreated.setSenderPhone(senderPhone);
			poCreated.setSenderFax(senderFax);
			poCreated.setNotes(notes);
			poCreated.setCustomer(customer);
			entityManager.persist(poCreated);
			
			Set<PoTool> poList = new HashSet<PoTool>();
			for (Entry<String, List<String>> eachEntry : matMap.entrySet()) {
				PoTool poTool = new PoTool();
				poTool.setPurchaseOrder(poCreated);
				poTool.setMatNo(eachEntry.getKey());
				poTool.setMatDesc(eachEntry.getValue().get(0));
				poTool.setMatUnitprice(eachEntry.getValue().get(1));
				poTool.setMatQuantiy(Integer.valueOf(eachEntry.getValue().get(2)));
				poTool.setDiscount(eachEntry.getValue().get(3));
				poTool.setMatValue(eachEntry.getValue().get(4));
				entityManager.persist(poTool);
				//poList.add(poTool);
			}
			
			//poCreated.setPoTools(poList);
			//entityManager.merge(poCreated);
			return poCreated;
	
		}
	}

	public PurchaseOrder addFilesToPO(int pid, Set<FilesUpload> filesUploads) {
		PurchaseOrder purchaseOrder = findPOById(pid);
		if(purchaseOrder!=null && purchaseOrder.getPid()>0){
				EntityManager entityManager = getEntityManager();
				purchaseOrder.setFilesUploads(filesUploads);	
				entityManager.merge(purchaseOrder);
		}
		//cleanUpFiles();
		return purchaseOrder;
	}
}