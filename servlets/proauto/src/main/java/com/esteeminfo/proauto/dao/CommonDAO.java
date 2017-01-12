package com.esteeminfo.proauto.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.esteeminfo.proauto.dto.MachineDTO;
import com.esteeminfo.proauto.entity.JobOperation;
import com.esteeminfo.proauto.entity.Machine;
import com.esteeminfo.proauto.entity.PurchaseOrder;

@Repository("commonDAO")
public class CommonDAO extends AbstractDao{

	public static SimpleDateFormat ui_date_format =  new SimpleDateFormat("MM/dd/yyyy");

	public void loadRoleMap(Map<String, String> roleMap) {
			
	}
	
	public Machine findMachineById(int id){
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		Query q = entityManager.createQuery( "select e from Machine e where e.machineId=:eid");
		q.setParameter("eid", id);
		List<Machine> result = q.getResultList();
		if(result == null || result.size() ==0){
			return null;
		}
		Machine e = result.get(0);
		entityManager.getTransaction().commit();
		entityManager.close();
		return e;
	}
	
	public List<Machine> retrieveAllMachines(String machineSearched){
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		String query = "select e from Machine e";
		if (machineSearched != null && machineSearched.length() > 0) {
			query += " where e.machineDesc LIKE '" + machineSearched + "%'";
		}
		Query q = entityManager.createQuery(query);
		List<Machine> result = q.getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		return result;
	}

	public Machine registerMachine(String create, String mid, String mName, String mCode, String mAxle, String mCost) throws Exception{
		int machineId = (mid == null || mid.length() == 0 ) ? 0:Integer.valueOf(mid); 


		if (create.equalsIgnoreCase("false") && machineId > 0 ) {
			EntityManager entityManager = getEntityManager();
			entityManager.getTransaction().begin();
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
			entityManager.flush();
			entityManager.getTransaction().commit();
			entityManager.close();
			return existingMachine;
		}else{
			EntityManager entityManager = getEntityManager();
			entityManager.getTransaction().begin();
			Machine machineCreated =  new Machine();
			machineCreated.setMachineDesc(mName);
			machineCreated.setMachineCodeType(mCode);
			machineCreated.setMachineAxle(mAxle);
			machineCreated.setMachineCost(mCost);
			entityManager.persist(machineCreated);
			entityManager.flush();
			entityManager.getTransaction().commit();
			entityManager.close();
			return machineCreated;
		}
	}

	public PurchaseOrder findPOById(String valueOf) {
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		Query q = entityManager.createQuery( "select e from PurchaseOrder e where e.pid=:pid");
		q.setParameter("pid", Integer.valueOf(valueOf));
		List<PurchaseOrder> result = q.getResultList();
		if(result == null || result.size() ==0){
			return null;
		}
		PurchaseOrder e = result.get(0);
		entityManager.getTransaction().commit();
		entityManager.close();
		return e;
	}

	public List<PurchaseOrder> retrieveAllPos(String poSearched) {
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		String query = "select e from PurchaseOrder e";
		if (poSearched != null && poSearched.length() > 0) {
			query += " where e.poId LIKE '" + poSearched + "%'";
		}
		Query q = entityManager.createQuery(query);
		List<PurchaseOrder> result = q.getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		return result;
	}

	public PurchaseOrder registerPO(String create, String pid, String poNumber, String poVersion, String poDate, String vnoSender,
			String poSender, String poSenderDetails, String senderEmail, String senderPhone, String senderFax,
			String notes, String matNo, String matDesc, String unitPrice, String quantity, String discount,
			String value) throws ParseException {
		int purchaseid = (pid == null || pid.length() == 0 ) ? 0:Integer.valueOf(pid); 
		java.util.Date javaDatePoDate = null;
		if(poDate!=null){
			javaDatePoDate = ui_date_format.parse(poDate) ;
		}
		
		if (create.equalsIgnoreCase("false") && purchaseid > 0) {
			EntityManager entityManager = getEntityManager();
			entityManager.getTransaction().begin();
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
			existingPO.setDiscount(discount);
			entityManager.persist(existingPO);
			entityManager.flush();
			entityManager.getTransaction().commit();
			entityManager.close();
			return existingPO;
		}else{
			EntityManager entityManager = getEntityManager();
			entityManager.getTransaction().begin();
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
			poCreated.setDiscount(discount);
			entityManager.persist(poCreated);
			entityManager.flush();
			entityManager.getTransaction().commit();
			entityManager.close();
			return poCreated;
		}
	}

	public JobOperation findOperationById(Integer valueOf) {
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		Query q = entityManager.createQuery( "select e from JobOperation e where e.joId=:eid");
		q.setParameter("eid", valueOf);
		List<JobOperation> result = q.getResultList();
		if(result == null || result.size() ==0){
			return null;
		}
		JobOperation e = result.get(0);
		entityManager.getTransaction().commit();
		entityManager.close();
		return e;
	}

	public List<JobOperation> retrieveAllOperations(String operationSearched) {
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		String query = "select e from JobOperation e";
		if (operationSearched != null && operationSearched.length() > 0) {
			query += " where e.jobName LIKE '" + operationSearched + "%'";
		}
		Query q = entityManager.createQuery(query);
		List<JobOperation> result = q.getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		return result;
	}

	public JobOperation registerOperation(String create, String oid, String oName, String oDescription) {
		int joid = (oid == null || oid.length() == 0 ) ? 0:Integer.valueOf(oid); 


		if (create.equalsIgnoreCase("false") && joid > 0 ) {
			EntityManager entityManager = getEntityManager();
			entityManager.getTransaction().begin();
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
			entityManager.flush();
			entityManager.getTransaction().commit();
			entityManager.close();
			return existingMachine;
		}else{
			EntityManager entityManager = getEntityManager();
			entityManager.getTransaction().begin();
			JobOperation machineCreated =  new JobOperation();
			machineCreated.setJobName(oName);
			machineCreated.setJobDesc(oDescription);
			entityManager.persist(machineCreated);
			entityManager.flush();
			entityManager.getTransaction().commit();
			entityManager.close();
			return machineCreated;
		}	
		
	}
	
	
	
}