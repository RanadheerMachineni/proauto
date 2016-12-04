package com.esteeminfo.proauto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.esteeminfo.proauto.entity.Employee;

@Repository("employeeDao")
public class EmployeeDaoImpl extends AbstractDao implements EmployeeDao {

	
	public Employee findById(int id) {
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		Employee e =  entityManager.find(Employee.class, id);
		entityManager.getTransaction().commit();
		entityManager.close();
		System.out.println("***** EmployeeDaoImpl findById = "+e.getFirstName());
		return e;
	}

	public List<Employee> retrieveAllEmployees(String employeeSearched) {
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		List<Employee> result = entityManager.createQuery( "from Employee", Employee.class ).getResultList();
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
}
