package com.esteeminfo.proauto.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.esteeminfo.proauto.entity.Customer;
import com.esteeminfo.proauto.entity.Department;
import com.esteeminfo.proauto.entity.Employee;
import com.esteeminfo.proauto.entity.FilesUpload;
import com.esteeminfo.proauto.entity.Role;
import com.esteeminfo.proauto.entity.Section;
import com.esteeminfo.proauto.service.EmployeeServiceImpl;

@Repository("customerDAO")
public class CustomerDAOImpl extends AbstractDao implements CustomerDAO {

	final static Logger logger = Logger.getLogger(EmployeeDaoImpl.class);

	public static SimpleDateFormat ui_date_format =  new SimpleDateFormat("MM/dd/yyyy");
	
	@Autowired(required=true)
	private FileUploadDAO fileUploadDAO;
	
	public Customer findById(int id) {
		EntityManager entityManager = getEntityManager();
		entityManager.getTransaction().begin();
		Query q = entityManager.createQuery( "select e from Customer e where e.customerId=:eid");
		q.setParameter("eid", id);
		List<Customer> result = q.getResultList();
		if(result == null || result.size() ==0){
			return null;
		}
		Customer e = result.get(0);
		entityManager.getTransaction().commit();
		entityManager.close();
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
		entityManager.getTransaction().begin();
		String query = "select e from Customer e";
		if (customerSearched != null && customerSearched.length() > 0) {
			query += " where e.customerName LIKE '" + customerSearched + "%'";
		}
		Query q = entityManager.createQuery(query);
		List<Customer> result = q.getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		return result;
	}

	public Customer addFilesToCustomer(int id, Set<FilesUpload> filesUploads) {
		Customer employee = findById(id);
		if(employee!=null && employee.getCustomerId()>0){
				EntityManager entityManager = getEntityManager();
				entityManager.getTransaction().begin();
				employee.setFilesUploads(filesUploads);	
				entityManager.merge(employee);
				entityManager.flush();
				entityManager.getTransaction().commit();
				entityManager.close();
		}
		cleanUpFiles();
		return employee;
	}

	public void cleanUpFiles() {
		
	}

	public Customer registerCustomer(String create, String cid, String cName, String cAddress,
			Map<String, List<String>> contactsMap)
			throws Exception {

		
		int employeeId = (cid == null || cid.length() == 0 ) ? 0:Integer.valueOf(cid); 


		if (create.equalsIgnoreCase("false") && employeeId > 0 ) {
			EntityManager entityManager = getEntityManager();
			entityManager.getTransaction().begin();
			Customer existingEmployee = null;
			Query q = entityManager.createQuery( "select e from Customer e where e.customerId=:eid");
			q.setParameter("eid", employeeId);
			List<Customer> result = q.getResultList();
			if(result != null || result.size() > 0){
				existingEmployee = result.get(0);
			}
			//Employee existingEmployee =  findById(Integer.valueOf(eid));
			System.out.println("updating existing cust ******** "+existingEmployee.getCustomerId());

			existingEmployee.setCustomerName(cName);
			existingEmployee.setAddress(cAddress);
			entityManager.persist(existingEmployee);
			entityManager.flush();

			Set<Contact> contactList = new HashSet<Contact>();
			for (Entry<String, List<String>> eachEntry : contactsMap.entrySet()) {
				Contact contact = new Contact();
				contact.setName(eachEntry.getKey());
				contact.setPhone(eachEntry.getValue().get(0));
				contact.setEmail(eachEntry.getValue().get(1));
				contact.setFax(eachEntry.getValue().get(2));
				contact.setNotes(eachEntry.getValue().get(3));
				entityManager.persist(contact);
				entityManager.flush();
				contactList.add(contact);
			}
			existingEmployee.setContacts(contactList);
			entityManager.merge(existingEmployee);
			entityManager.flush();
			entityManager.getTransaction().commit();
			entityManager.close();
			return existingEmployee;
		}else{
			EntityManager entityManager = getEntityManager();
			entityManager.getTransaction().begin();
			Customer employeeCreated =  new Customer();
			employeeCreated.setCustomerName(cName);
			employeeCreated.setAddress(cAddress);
			entityManager.persist(employeeCreated);
			entityManager.flush();
			entityManager.getTransaction().commit();
			entityManager.close();
			
			EntityManager entityManager1 = getEntityManager();
			entityManager1.getTransaction().begin();
			Query q = entityManager1.createQuery( "select e from Customer e where e.customerId=:eid");
			q.setParameter("eid", employeeCreated.getCustomerId());
			List<Customer> result = q.getResultList();
			Customer employeeCreated1 = result.get(0);
			Set<Contact> contactList = new HashSet<Contact>();
			for (Entry<String, List<String>> eachEntry : contactsMap.entrySet()) {
				Contact contact = new Contact();
				contact.setName(eachEntry.getKey());
				contact.setPhone(eachEntry.getValue().get(0));
				contact.setEmail(eachEntry.getValue().get(1));
				contact.setFax(eachEntry.getValue().get(2));
				contact.setNotes(eachEntry.getValue().get(3));
				contactList.add(contact);
				entityManager1.persist(contact);
				entityManager1.flush();
			}
			employeeCreated1.setContacts(contactList);
			entityManager1.merge(employeeCreated1);
			entityManager1.flush();
			entityManager1.getTransaction().commit();
			entityManager1.close();
			return employeeCreated1;
		}
	
	}
	
}
