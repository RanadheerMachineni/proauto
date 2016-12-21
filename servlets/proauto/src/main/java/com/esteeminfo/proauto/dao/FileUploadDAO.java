package com.esteeminfo.proauto.dao;

import com.esteeminfo.proauto.entity.FilesUpload;

public interface FileUploadDAO {

	FilesUpload saveFile(String fileName,byte[] fileData);
	
	boolean deleteFile(int uploadId);
}
