package com.esteeminfo.proauto.service;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.esteeminfo.proauto.controllers.AppController;
import com.esteeminfo.proauto.dao.CustomerDAO;
import com.esteeminfo.proauto.dao.EmployeeDao;
import com.esteeminfo.proauto.dao.FileUploadDAO;
import com.esteeminfo.proauto.dto.CustomerDTO;
import com.esteeminfo.proauto.dto.EmployeeDTO;
import com.esteeminfo.proauto.entity.Contact;
import com.esteeminfo.proauto.entity.Customer;
import com.esteeminfo.proauto.entity.Employee;
import com.esteeminfo.proauto.entity.FilesUpload;
import com.esteeminfo.proauto.entity.Role;

@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

	final static Logger logger = Logger.getLogger(CustomerServiceImpl.class);

	public static SimpleDateFormat ui_date_format =  new SimpleDateFormat("MM/dd/yyyy");
	
	@Autowired(required=true)
	private CustomerDAO customerDAO ;

	@Autowired(required=true)
	private FileUploadDAO fileUploadDAO ;
	
	
	public Customer findById(int id) {
		return customerDAO.findById(id);	
	}

	public Customer findByName(String name) {
		return customerDAO.findByName(name);	
	}

	public List<Customer> retrieveAllCustomer(String customerSearched) {
		return customerDAO.retrieveAllCustomers(customerSearched);
	}

	public CustomerDTO converCustomerToDto(Customer customer) {
		CustomerDTO customerDTO =  new CustomerDTO();
		customerDTO.setCustomerId(customer.getCustomerId());
		customerDTO.setAddress(customer.getAddress());
		customerDTO.setCustomerName(customer.getCustomerName());
		if(customer.getFilesUploads()!=null && customer.getFilesUploads().size()>0){
			List<String> files =  new ArrayList<String>();
			for(FilesUpload filesUpload: customer.getFilesUploads()){
				files.add(filesUpload.getFileName());
			}
			customerDTO.setFiles(files);
		}	
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

	public FilesUpload findFile(Integer cid, String fname) {
		Customer e = findById(cid);
		Set<FilesUpload> files = e.getFilesUploads();
		for(FilesUpload filesUpload : files){
			if(filesUpload.getFileName().equalsIgnoreCase(fname)){
				return filesUpload;
			}
		}
		return null;
	}

	public Customer registerCustomer(String create, String cid, String cName, String cAddress,
			Map<String, List<String>> contactsMap, MultipartFile[] files, List<String> uploadedFilesTrimmed )
			throws Exception {

		Customer customerCreated = customerDAO.registerCustomer(create, cid, cName, cAddress,
				 contactsMap);
		
		Set<FilesUpload> filesUploads = new HashSet<FilesUpload>();
		if(files!=null){
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				if(file.getOriginalFilename()!=null && file.getOriginalFilename().length()>0){
					logger.info("uploading file "+file.getOriginalFilename());
					try {
						byte[] bytes = file.getBytes();
						FilesUpload filesUpload =  fileUploadDAO.saveFile(file.getOriginalFilename(), bytes);
						filesUploads.add(filesUpload);
						
					} catch (Exception e) {
					}	
				}
				
			}
		}
		Set<FilesUpload> existingFiles = customerCreated.getFilesUploads();
		Set<FilesUpload> existingFilesNew = new HashSet<FilesUpload>();
		if(existingFiles!=null){
			for(FilesUpload existingFile : existingFiles){
				if(uploadedFilesTrimmed.contains(existingFile.getFileName())){
					existingFilesNew.add(existingFile);
				}
			}
		}
		filesUploads.addAll(existingFilesNew);
		customerCreated = customerDAO.addFilesToCustomer(customerCreated.getCustomerId(), filesUploads);
		return customerCreated;
	
	}
	
}
