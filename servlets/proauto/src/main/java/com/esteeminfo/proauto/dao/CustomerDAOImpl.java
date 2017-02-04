package com.esteeminfo.proauto.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.esteeminfo.proauto.entity.Contact;
import com.esteeminfo.proauto.entity.CusFileData;
import com.esteeminfo.proauto.entity.Customer;
import com.esteeminfo.proauto.entity.CustomerFile;
import com.esteeminfo.proauto.entity.Department;
import com.esteeminfo.proauto.entity.EmpFileData;
import com.esteeminfo.proauto.entity.Employee;
import com.esteeminfo.proauto.entity.EmployeeFile;
import com.esteeminfo.proauto.entity.Jobcard;
import com.esteeminfo.proauto.entity.Role;
import com.esteeminfo.proauto.entity.Section;
import com.esteeminfo.proauto.service.EmployeeServiceImpl;

@Repository("customerDAO")
public class CustomerDAOImpl extends AbstractDao implements CustomerDAO {

	final static Logger logger = Logger.getLogger(EmployeeDaoImpl.class);

	public static SimpleDateFormat ui_date_format =  new SimpleDateFormat("MM/dd/yyyy");
	
	public Customer findById(int id) {
		EntityManager entityManager = getEntityManager();
		Query q = entityManager.createQuery( "select e from Customer e where e.customerId=:eid");
		q.setParameter("eid", id);
		List<Customer> result = q.getResultList();
		if(result == null || result.size() ==0){
			return null;
		}
		Customer e = result.get(0);
		return e;
	}

	public Customer findByName(String name) {
		EntityManager entityManager = getEntityManager();
		Query q = entityManager.createQuery( "select e from Customer e where e.customerName=:name");
		q.setParameter("name", name);
		List<Customer> result = q.getResultList();
		if(result == null || result.size() ==0){
			return null;
		}
		return result.get(0);
	}

	public List<Customer> retrieveAllCustomers(String customerSearched) {
		EntityManager entityManager = getEntityManager();
		String query = "select e from Customer e";
		if (customerSearched != null && customerSearched.length() > 0) {
			query += " where e.customerName LIKE '" + customerSearched + "%'";
		}
		Query q = entityManager.createQuery(query);
		List<Customer> result = q.getResultList();
		return result;
	}

	public void cleanUpFiles() {
		
	}

	public Customer registerCustomer(String create, String cid, String cName, String cAddress,
			Map<String, List<String>> contactsMap, MultipartFile[] files, String removedFiles) throws Exception {
		int customerId = (cid == null || cid.length() == 0 ) ? 0:Integer.valueOf(cid); 


		Customer customer = null;
		if (create.equalsIgnoreCase("false") && customerId > 0 ) {
			customer = findById(customerId);
			customer.getContacts().clear();
			if(removedFiles!=null && removedFiles.length()>0){
				List<String> removedFilesArray = new ArrayList<String>();
				removedFilesArray.addAll(Arrays.asList(removedFiles.split(",")));
				List<CustomerFile> listTobeRemoved = new ArrayList<CustomerFile>();
				for(String file : removedFilesArray){
					for(CustomerFile eF : customer.getCustomerFiles()){
						if(eF.getFileName().equalsIgnoreCase(file)){
							listTobeRemoved.add(eF);
						}
					}
				}
				for(CustomerFile rf:listTobeRemoved){
					customer.removeCustomerFile(rf);
				}
			}
		
		}else{
			customer = new Customer();
		}
		
		customer.setCustomerName(cName);
		customer.setAddress(cAddress);
		entityManager.persist(customer);

		Set<Contact> contactList = new HashSet<Contact>();
		for (Entry<String, List<String>> eachEntry : contactsMap.entrySet()) {
			Contact contact = new Contact();
			contact.setName(eachEntry.getKey());
			contact.setPhone(eachEntry.getValue().get(0));
			contact.setEmail(eachEntry.getValue().get(1));
			contact.setFax(eachEntry.getValue().get(2));
			contact.setNotes(eachEntry.getValue().get(3));
			entityManager.persist(contact);
			contactList.add(contact);
		}
		if (customer.getContacts()!=null) {
			customer.getContacts().addAll(contactList);
		}else{
			customer.setContacts(contactList);
		}
		
		if(files!=null){
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				if(file.getOriginalFilename()!=null && file.getOriginalFilename().length()>0){
					logger.info("uploading file "+file.getOriginalFilename());
					try {
						CustomerFile customerFile =  new CustomerFile();
						customerFile.setFileName(file.getOriginalFilename());
						customerFile.setCustomer(customer);
						entityManager.persist(customerFile);

						byte[] bytes = file.getBytes();
						CusFileData empFileData =  new CusFileData();
						empFileData.setFileData(bytes);
						empFileData.setCustomerFile(customerFile);
						entityManager.persist(empFileData);

						customer.addCustomerFile(customerFile);
						
					} catch (Exception e) {
					}	
				}
				
			}
		}
		
		entityManager.persist(customer);
		return customer;
	}

	public List<String> findFileNames(int id) {
		List<String> list = null;
		Query q1 = entityManager.createNativeQuery("select file_name from customer_files where customer_id="+id);
		list= q1.getResultList();
		return list;
	}

	public byte[] findFileData(Integer cid, String fileName) {
		Query query= entityManager.createNativeQuery("select cfd.* from customer_files cf,cus_file_data cfd where cf.upload_id=cfd.upload_id and cf.file_name='"+fileName+"' and cf.customer_id="+cid,CusFileData.class);
		CusFileData cusFileData = (CusFileData) query.getSingleResult();
		return cusFileData.getFileData();
	}
	
}
