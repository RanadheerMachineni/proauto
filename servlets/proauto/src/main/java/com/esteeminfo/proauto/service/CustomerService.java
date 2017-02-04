package com.esteeminfo.proauto.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.esteeminfo.proauto.dto.CustomerDTO;
import com.esteeminfo.proauto.entity.Customer;

public interface CustomerService {

	Customer findById(int id);
	
	CustomerDTO findDTOById(int id);

	Customer findByName(String name);

	List<Customer> retrieveAllCustomer(String customerSearched);
	
	List<CustomerDTO> retrieveAllCustomerDTO(String employeeSearched);

	Customer registerCustomer(String create, String cid, String cName, String cAddress, Map<String, List<String>> contactsMap,MultipartFile[] files,  String removedFiles) throws Exception;
	
	CustomerDTO converCustomerToDto(Customer customer,List<String> fileNames);

	Map<String, String> retreiveCustomerMap();

	byte[] findFile(Integer valueOf, String fileNameFromUI);

}
