package com.esteeminfo.proauto.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.esteeminfo.proauto.dto.MachineDTO;
import com.esteeminfo.proauto.entity.Machine;

@Repository("commonDAO")
public class CommonDAO extends AbstractDao{

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
	
	
	
}