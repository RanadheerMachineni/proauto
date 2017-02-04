package com.esteeminfo.proauto.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.esteeminfo.proauto.dao.CustomerDAO;
import com.esteeminfo.proauto.dto.CustomerDTO;
import com.esteeminfo.proauto.entity.Contact;
import com.esteeminfo.proauto.entity.Customer;
import com.esteeminfo.proauto.entity.CustomerFile;

@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

	final static Logger logger = Logger.getLogger(CustomerServiceImpl.class);

	public static SimpleDateFormat ui_date_format =  new SimpleDateFormat("MM/dd/yyyy");
	
	@Autowired(required=true)
	private CustomerDAO customerDAO ;

	
	public Customer findById(int id) {
		return customerDAO.findById(id);	
	}

	public Customer findByName(String name) {
		return customerDAO.findByName(name);	
	}

	public List<Customer> retrieveAllCustomer(String customerSearched) {
		return customerDAO.retrieveAllCustomers(customerSearched);
	}

	public CustomerDTO converCustomerToDto(Customer customer, List<String> fileNames) {
		CustomerDTO customerDTO =  new CustomerDTO();
		customerDTO.setCustomerId(customer.getCustomerId());
		customerDTO.setAddress(customer.getAddress());
		customerDTO.setCustomerName(customer.getCustomerName());
		customerDTO.setFiles(fileNames);
		if(customer.getContacts()!=null && customer.getContacts().size()>0){
			List<String> contactss =  new ArrayList<String>();
			for(Contact contact: customer.getContacts()){
				if(contact!=null && contact.getName()!=null && contact.getName().length()>0){
					contactss.add(contact.getName()+"|"+contact.getPhone()+"|"+contact.getEmail()+"|"+contact.getFax()+"|"+contact.getNotes());
				}
			}
			customerDTO.setContacts(contactss);
		}	
		
		return customerDTO;
	}
	public CustomerDTO converCustomerToMiniDto(Customer customer) {
		CustomerDTO customerDTO =  new CustomerDTO();
		customerDTO.setCustomerId(customer.getCustomerId());
		customerDTO.setAddress(customer.getAddress());
		customerDTO.setCustomerName(customer.getCustomerName());
		return customerDTO;
	}
	
	public Customer registerCustomer(String create, String cid, String cName, String cAddress,
			Map<String, List<String>> contactsMap, MultipartFile[] files, String removedFiles )
			throws Exception {

		Customer customerCreated = customerDAO.registerCustomer(create, cid, cName, cAddress,
				 contactsMap,files, removedFiles);
		
		return customerCreated;
	
	}

	public Map<String, String> retreiveCustomerMap() {
		Map<String, String> map = new HashMap<String, String>();
		List<Customer> list = customerDAO.retrieveAllCustomers(null);
		for(Customer eachCustomer : list){
			map.put(String.valueOf(eachCustomer.getCustomerId()), eachCustomer.getCustomerName());
		}
		
		return map;
	}

	public CustomerDTO findDTOById(int id) {
		Customer customer= findById(id);
		List<String> fileNames = customerDAO.findFileNames(id);
		if(customer!=null) return converCustomerToDto(customer,fileNames);
		return null;
	}

	public List<CustomerDTO> retrieveAllCustomerDTO(String customerSearched) {
		List<CustomerDTO> customerDTOs = new ArrayList<CustomerDTO>();
		List<Customer> customers = retrieveAllCustomer(customerSearched);
		for(Customer eachCust : customers){
			CustomerDTO customerDTO = converCustomerToMiniDto(eachCust);
			customerDTOs.add(customerDTO);
		}
		return customerDTOs;
	}

	public byte[] findFile(Integer valueOf, String fileName) {
		byte[] fileData = customerDAO.findFileData(valueOf, fileName);
		return fileData;
	}
	
}
