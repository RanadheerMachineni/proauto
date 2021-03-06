package com.esteeminfo.proauto.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.esteeminfo.proauto.dto.EmployeeDTO;
import com.esteeminfo.proauto.entity.Employee;

public interface EmployeeService {

	Employee findById(int id);

	EmployeeDTO findDTOById(int id);

	Employee findByUser(String user);

	List<Employee> retrieveAllEmployees(String employeeSearched);
	
	List<EmployeeDTO> retrieveAllEmployeesDTO(String employeeSearched);

	Employee registerEmployee(String create, String eid, String efirstName, String eLastName, String gender,
			String eQualification, String eExperience, String married, String eDesignation, String eDob, String eDoj,
			String eRole, String eUserId, String password, String ePhone, String eEmail, String ePassport,
			String eEmergencyContact, String eCAddress, String ePAddress, String eNotes, String eEmploymentType, String eSection, MultipartFile[] files,String removedFiles) throws Exception;
	
	EmployeeDTO converEmployeeToDto(Employee employee,List<String> fileNames);

	byte[] findFile(Integer valueOf, String fileNameFromUI);

	Map<String, String> getEmployees();

	//FilesUpload findFile(Integer eid, String fname);

}
