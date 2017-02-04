package com.esteeminfo.proauto.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="customer_id")
	private int customerId;

	private String address;

	private String city;

	@Temporal(TemporalType.DATE)
	@Column(name="create_date")
	private Date createDate;

	@Column(name="customer_name")
	private String customerName;

	private String state;

	@Column(name="zip_code")
	private String zipCode;
	
	//bi-directional many-to-many association to Contact
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="customer_contacts"
		, joinColumns={
			@JoinColumn(name="customer_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="contact_id")
			}
		)
	private Set<Contact> contacts;
	

	//bi-directional many-to-one association to CustomerFile
	@OneToMany(mappedBy="customer", fetch=FetchType.LAZY, cascade=CascadeType.ALL,orphanRemoval=true)
	private Set<CustomerFile> customerFiles;

	//bi-directional many-to-one association to PurchaseOrder
	@OneToMany(mappedBy="customer", fetch=FetchType.LAZY)
	private Set<PurchaseOrder> purchaseOrders;
	
	
	@OneToMany(mappedBy="customer",fetch=FetchType.LAZY)
	private Set<Jobcard> jobcards;
	
	public Customer() {
	}

	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Set<CustomerFile> getCustomerFiles() {
		return this.customerFiles;
	}

	public void setCustomerFiles(Set<CustomerFile> customerFiles) {
		this.customerFiles = customerFiles;
	}

	public CustomerFile addCustomerFile(CustomerFile customerFile) {
		getCustomerFiles().add(customerFile);
		customerFile.setCustomer(this);

		return customerFile;
	}

	public CustomerFile removeCustomerFile(CustomerFile customerFile) {
		getCustomerFiles().remove(customerFile);
		customerFile.setCustomer(null);

		return customerFile;
	}

	public Set<Contact> getContacts() {
		return this.contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

	public Set<PurchaseOrder> getPurchaseOrders() {
		return this.purchaseOrders;
	}

	public void setPurchaseOrders(Set<PurchaseOrder> purchaseOrders) {
		this.purchaseOrders = purchaseOrders;
	}

	public PurchaseOrder addPurchaseOrder(PurchaseOrder purchaseOrder) {
		getPurchaseOrders().add(purchaseOrder);
		purchaseOrder.setCustomer(this);
		return purchaseOrder;
	}

	public PurchaseOrder removePurchaseOrder(PurchaseOrder purchaseOrder) {
		getPurchaseOrders().remove(purchaseOrder);
		purchaseOrder.setCustomer(null);
		return purchaseOrder;
	}

	public Set<Jobcard> getJobcards() {
		return jobcards;
	}

	public void setJobcards(Set<Jobcard> jobcards) {
		this.jobcards = jobcards;
	}
	
	public Jobcard addJobcard(Jobcard jobcard) {
		getJobcards().add(jobcard);
		jobcard.setCustomer(this);
		return jobcard;
	}

	public Jobcard removeJobcard(Jobcard jobcard) {
		getJobcards().remove(jobcard);
		jobcard.setCustomer(null);
		return jobcard;
	}

}