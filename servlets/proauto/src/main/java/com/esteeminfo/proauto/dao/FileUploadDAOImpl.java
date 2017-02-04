package com.esteeminfo.proauto.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.esteeminfo.proauto.entity.EmpFileData;
import com.esteeminfo.proauto.entity.Employee;
import com.esteeminfo.proauto.entity.EmployeeFile;

@Repository("fileUploadDAO")
public class FileUploadDAOImpl extends AbstractDao implements FileUploadDAO{

	public boolean deleteFile(int uploadId) {
		return false;
	}

	public EmployeeFile saveEmployeeFile(int empId, String originalFilename, byte[] bytes) {
		EntityManager entityManager = getEntityManager();
		EmpFileData empFileData =  new EmpFileData();
		empFileData.setFileData(bytes);
		entityManager.persist(empFileData);

		EmployeeFile employeeFile =  new EmployeeFile();
		employeeFile.setFileName(originalFilename);
		employeeFile.setEmpFileData(empFileData);
		
		return employeeFile;
	}

}
