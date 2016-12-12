package com.esteeminfo.proauto.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.esteeminfo.proauto.entity.Employee;
import com.esteeminfo.proauto.entity.Role;

@Repository("employeeDao")
public class EmployeeDaoImpl extends AbstractDao implements EmployeeDao {

	public static SimpleDateFormat ui_date_format =  new SimpleDateFormat("MM/dd/yyyy");

	
	public Employee findById(int id) {
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		Query q = entityManager.createQuery( "select e from Employee e join fetch e.roles where e.employeeId=:eid");
		q.setParameter("eid", id);
		List<Employee> result = q.getResultList();
		if(result == null || result.size() ==0){
			return null;
		}
		Employee e = result.get(0);
		entityManager.getTransaction().commit();
		entityManager.close();
		System.out.println("***** EmployeeDaoImpl findById = "+e.getFirstName());
		return e;
	}

	public List<Employee> retrieveAllEmployees(String employeeSearched) {
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		Query q = entityManager.createQuery( "select e from Employee e join fetch e.roles");
		List<Employee> result = q.getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		System.out.println("***** EmployeeDaoImpl retrieveAllEmployees = "+result.get(0).getFirstName());
		return result;
	}

	public Employee findByUser(String user) {
		EntityManager entityManager = getEntityManager();
		Query q = entityManager.createQuery( "select e from Employee e join fetch e.roles where e.userId=:userId");
		q.setParameter("userId", user);
		List<Employee> result = q.getResultList();
		if(result == null || result.size() ==0){
			return null;
		}
		return result.get(0);
	}

	public boolean registerEmployee(String create, String eid, String efirstName, String eLastName, String gender,
			String eQualification, String eExperience, String married, String eDesignation, String eDob, String eDoj,
			String eRole, String eUserId, String password, String ePhone, String eEmail, String ePassport,
			String eEmergencyContact, String eCAddress, String ePAddress, String eNotes) throws Exception {
		
		Employee existingEmployeeByUser =  findByUser(eUserId);
		if (create.equalsIgnoreCase("true") && existingEmployeeByUser!=null) {
			throw new Exception("Employee with given UserId already exist. Please select other UserId");
		}
		java.util.Date javaDateDob = ui_date_format.parse(eDob) ;
		java.util.Date javaDateDoj = ui_date_format.parse(eDoj) ;

		if (create.equalsIgnoreCase("false") && eid != null) {
			EntityManager entityManager = getEntityManager();
			Employee existingEmployee =  findById(Integer.valueOf(eid));
			existingEmployee.setFirstName(efirstName);
			existingEmployee.setLastName(eLastName);
			existingEmployee.setUserId(eUserId);
			existingEmployee.setGender(gender);
			existingEmployee.setQualification(eQualification);
			existingEmployee.setExperience(eExperience);
			existingEmployee.setMarried(married);
			existingEmployee.setDesignation(eDesignation);
			existingEmployee.setDob(javaDateDob);
			existingEmployee.setDoj(javaDateDoj);
			/*Role role = entityManager.find(Role.class, eRole);
			List<Employee> empList = new ArrayList<Employee>();
			empList.add(existingEmployee);
			role.setEmployees(empList);*/
			Role role = entityManager.find(Role.class, eRole);
			List<Role> roleList = new ArrayList<Role>();
			roleList.add(role);
			existingEmployee.setRoles(roleList);
			entityManager.merge(existingEmployee);
		}else{
			EntityManager entityManager = getEntityManager();
			entityManager.getTransaction().begin();
			Employee employeeCreated =  new Employee();
			employeeCreated.setFirstName(efirstName);
			employeeCreated.setLastName(eLastName);
			employeeCreated.setUserId(eUserId);
			employeeCreated.setGender(gender);
			employeeCreated.setQualification(eQualification);
			employeeCreated.setExperience(eExperience);
			employeeCreated.setMarried(married);
			employeeCreated.setDesignation(eDesignation);
			employeeCreated.setDob(javaDateDob);
			employeeCreated.setDoj(javaDateDoj);
			Role role = entityManager.find(Role.class, eRole);
			List<Role> roleList = new ArrayList<Role>();
			roleList.add(role);
			employeeCreated.setRoles(roleList);
			entityManager.persist(employeeCreated);
			entityManager.persist(role);

			entityManager.flush();
			entityManager.getTransaction().commit();
			entityManager.close();
			/*entityManager.getTransaction().begin();
			System.out.println("emp Id 1="+employeeCreated.getEmployeeId());
			entityManager.refresh(employeeCreated);
			System.out.println("emp Id 2="+employeeCreated.getEmployeeId());
			Role role = entityManager.find(Role.class, eRole);
			List<Role> roleList = new ArrayList<Role>();
			roleList.add(role);
			employeeCreated.setRoles(roleList);
			entityManager.merge(employeeCreated);
			entityManager.flush();
			entityManager.getTransaction().commit();
			entityManager.close();*/

		}
		
		
		return true;
	}
	
	private static void deleteEmployeeRole(String eUserId){
		
	}
}
