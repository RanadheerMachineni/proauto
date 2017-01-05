package com.esteeminfo.proauto.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.esteeminfo.proauto.dto.CustomerDTO;
import com.esteeminfo.proauto.entity.Customer;
import com.esteeminfo.proauto.entity.FilesUpload;

public interface CustomerService {

	Customer findById(int id);
	
	Customer findByName(String name);

	List<Customer> retrieveAllCustomer(String customerSearched);

	Customer registerCustomer(String create, String cid, String cName, String cAddress, Map<String, List<String>> contactsMap,MultipartFile[] files,  List<String> uploadedFilesTrimmed) throws Exception;
	
	CustomerDTO converCustomerToDto(Customer customer);

	FilesUpload findFile(Integer eid, String fname);

}
