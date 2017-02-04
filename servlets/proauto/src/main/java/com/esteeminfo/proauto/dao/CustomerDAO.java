package com.esteeminfo.proauto.dao;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.esteeminfo.proauto.entity.Customer;

public interface CustomerDAO {

	Customer findById(int id);
	
	Customer findByName(String name);


	List<Customer> retrieveAllCustomers(String customerSearched);

	Customer registerCustomer(String create, String cid, String cName, String cAddress, Map<String, List<String>> contactsMap, MultipartFile[] files, String removedFiles) throws Exception;
	
	void  cleanUpFiles();

	List<String> findFileNames(int id);

	byte[] findFileData(Integer valueOf, String fileName);

}

