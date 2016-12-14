package com.esteeminfo.proauto.dao;

import java.util.List;

import com.esteeminfo.proauto.entity.Employee;

public interface EmployeeDao {

	Employee findById(int id);
	
	Employee findByUser(String user);


	List<Employee> retrieveAllEmployees(String employeeSearched);

	boolean registerEmployee(String create, String eid, String efirstName, String eLastName, String gender,
			String eQualification, String eExperience, String married, String eDesignation, String eDob, String eDoj,
			String eRole, String eUserId, String password, String ePhone, String eEmail, String ePassport,
			String eEmergencyContact, String eCAddress, String ePAddress, String eNotes) throws Exception;
	
}
