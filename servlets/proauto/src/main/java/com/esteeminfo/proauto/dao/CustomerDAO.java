package com.esteeminfo.proauto.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.esteeminfo.proauto.entity.Customer;
import com.esteeminfo.proauto.entity.Employee;
import com.esteeminfo.proauto.entity.FilesUpload;

public interface CustomerDAO {

	Customer findById(int id);
	
	Customer findByName(String name);


	List<Customer> retrieveAllCustomers(String customerSearched);

	Customer registerCustomer(String create, String cid, String cName, String cAddress, Map<String, List<String>> contactsMap) throws Exception;
	
	Customer addFilesToCustomer(int id, Set<FilesUpload> filesUploads);

	void  cleanUpFiles();

}

