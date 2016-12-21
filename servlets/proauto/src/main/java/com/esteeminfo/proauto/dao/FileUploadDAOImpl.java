package com.esteeminfo.proauto.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.esteeminfo.proauto.entity.FilesUpload;

@Repository("fileUploadDAO")
public class FileUploadDAOImpl extends AbstractDao implements FileUploadDAO{

	public FilesUpload saveFile(String fileName, byte[] fileData) {
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		FilesUpload filesUpload =  new FilesUpload();
		filesUpload.setFileName(fileName);
		filesUpload.setFileData(fileData);
		entityManager.persist(filesUpload);
		entityManager.flush();
		entityManager.getTransaction().commit();
		entityManager.close();
		return filesUpload;
	}

	public boolean deleteFile(int uploadId) {
		return false;
	}

}
