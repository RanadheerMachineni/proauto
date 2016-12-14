package com.esteeminfo.proauto.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.esteeminfo.proauto.entity.Department;
import com.esteeminfo.proauto.entity.Employee;
import com.esteeminfo.proauto.entity.Role;
import com.esteeminfo.proauto.entity.Section;

@Repository("employeeDao")
public class EmployeeDaoImpl extends AbstractDao implements EmployeeDao {

	public static SimpleDateFormat ui_date_format =  new SimpleDateFormat("MM/dd/yyyy");

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
		String query = "select e from Employee e join fetch e.roles";
		if (employeeSearched != null && employeeSearched.length() > 0) {
			query += " where e.firstName LIKE '" + employeeSearched + "%'";
		}
		Query q = entityManager.createQuery(query);
		List<Employee> result = q.getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		return result;
	}

	public boolean registerEmployee(String create, String eid, String efirstName, String eLastName, String gender,
			String eQualification, String eExperience, String married, String eDesignation, String eDob, String eDoj,
			String eRole, String eUserId, String password, String ePhone, String eEmail, String ePassport,
			String eEmergencyContact, String eCAddress, String ePAddress, String eNotes, String eEmploymentType, String eSection) throws Exception {
		
		int employeeId = (eid == null || eid.length() == 0 ) ? 0:Integer.valueOf(eid); 
		if(eUserId!=null){
			Employee existingEmployeeByUser =  findByUser(eUserId);
			if (existingEmployeeByUser!=null && (employeeId==0 || (existingEmployeeByUser.getEmployeeId() != employeeId))) {
				throw new Exception("Employee with given UserId already exist. Please select other UserId");
			}
		}
		java.util.Date javaDateDob = ui_date_format.parse(eDob) ;
		java.util.Date javaDateDoj = ui_date_format.parse(eDoj) ;

		if (create.equalsIgnoreCase("false") && employeeId > 0 ) {
			EntityManager entityManager = getEntityManager();
			entityManager.getTransaction().begin();
			Employee existingEmployee = null;
			Query q = entityManager.createQuery( "select e from Employee e join fetch e.roles where e.employeeId=:eid");
			q.setParameter("eid", employeeId);
			List<Employee> result = q.getResultList();
			if(result != null || result.size() > 0){
				existingEmployee = result.get(0);
			}
			//Employee existingEmployee =  findById(Integer.valueOf(eid));
			System.out.println("updating existing emp ******** "+existingEmployee.getEmployeeId());

			existingEmployee.setFirstName(efirstName);
			existingEmployee.setLastName(eLastName);
			existingEmployee.setUserId(eUserId);
			existingEmployee.setPassword(password);
			existingEmployee.setGender(gender);
			existingEmployee.setQualification(eQualification);
			existingEmployee.setExperience(eExperience);
			existingEmployee.setMarried(married);
			existingEmployee.setDesignation(eDesignation);
			existingEmployee.setDob(javaDateDob);
			existingEmployee.setDoj(javaDateDoj);
			existingEmployee.setPhone(ePhone);
			existingEmployee.setEmail(eEmail);
			existingEmployee.setPassport(ePassport);
			existingEmployee.setEmergencyContact(eEmergencyContact);
			existingEmployee.setCurrentAddress(eCAddress);
			existingEmployee.setPermanentAddress(ePAddress);
			existingEmployee.setNotes(eNotes);
			existingEmployee.setEmployementType(eEmploymentType);
			existingEmployee.setSection(entityManager.find(Section.class, eSection));
			existingEmployee.setDepartment(entityManager.find(Department.class, "dept1"));
			existingEmployee.setStatus("a");
			entityManager.persist(existingEmployee);
			entityManager.flush();
			Role role = entityManager.find(Role.class, eRole);
			List<Role> roleList = new ArrayList<Role>();
			roleList.add(role);
			existingEmployee.setRoles(roleList);
			entityManager.merge(role);
			entityManager.flush();
			entityManager.getTransaction().commit();
			entityManager.close();
		}else{
			EntityManager entityManager = getEntityManager();
			entityManager.getTransaction().begin();
			Employee employeeCreated =  new Employee();
			employeeCreated.setFirstName(efirstName);
			employeeCreated.setLastName(eLastName);
			employeeCreated.setUserId(eUserId);
			employeeCreated.setPassword(password);
			employeeCreated.setGender(gender);
			employeeCreated.setQualification(eQualification);
			employeeCreated.setExperience(eExperience);
			employeeCreated.setMarried(married);
			employeeCreated.setDesignation(eDesignation);
			employeeCreated.setDob(javaDateDob);
			employeeCreated.setDoj(javaDateDoj);
			employeeCreated.setPhone(ePhone);
			employeeCreated.setEmail(eEmail);
			employeeCreated.setPassport(ePassport);
			employeeCreated.setEmergencyContact(eEmergencyContact);
			employeeCreated.setCurrentAddress(eCAddress);
			employeeCreated.setPermanentAddress(ePAddress);
			employeeCreated.setNotes(eNotes);
			employeeCreated.setDepartment(entityManager.find(Department.class, "dept1"));
			employeeCreated.setStatus("a");
			employeeCreated.setEmployementType(eEmploymentType);
			employeeCreated.setSection(entityManager.find(Section.class, eSection));
			entityManager.persist(employeeCreated);
			entityManager.flush();
			System.out.println("EMP created ******** "+employeeCreated.getEmployeeId());
			Role role = entityManager.find(Role.class, eRole);
			List<Role> roleList = new ArrayList<Role>();
			roleList.add(role);
			employeeCreated.setRoles(roleList);
			entityManager.merge(role);
			entityManager.flush();
			entityManager.getTransaction().commit();
			entityManager.close();
		}
		
		
		return true;
	}
	
	private static void deleteEmployeeRole(String eUserId){
		
	}
}
