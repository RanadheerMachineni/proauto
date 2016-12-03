package com.esteeminfo.proauto.dao;

import java.util.List;

import com.esteeminfo.proauto.entity.Employee;

public interface EmployeeDao {

	Employee findById(int id);
	
	Employee findByUser(String user);


	List<Employee> retrieveAllEmployees(String employeeSearched);
	
}

