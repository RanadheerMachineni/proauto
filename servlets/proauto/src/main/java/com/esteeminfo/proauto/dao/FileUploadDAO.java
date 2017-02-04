package com.esteeminfo.proauto.dao;

import com.esteeminfo.proauto.entity.EmployeeFile;

public interface FileUploadDAO {

	
	boolean deleteFile(int uploadId);

	EmployeeFile saveEmployeeFile(int empId, String originalFilename, byte[] bytes);
}
