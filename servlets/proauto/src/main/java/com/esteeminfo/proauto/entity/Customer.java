package com.esteeminfo.proauto.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
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
	@ManyToMany
	@JoinTable(
		name="customer_contacts"
		, joinColumns={
			@JoinColumn(name="customer_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="contact_id")
			}
		)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Contact> contacts;

	//uni-directional many-to-many association to FilesUpload
	@ManyToMany
	@JoinTable(
		name="customer_files"
		, joinColumns={
			@JoinColumn(name="customer_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="upload_id")
			}
		)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<FilesUpload> filesUploads;
	
	
	public Set<FilesUpload> getFilesUploads() {
		return filesUploads;
	}

	public void setFilesUploads(Set<FilesUpload> filesUploads) {
		this.filesUploads = filesUploads;
	}

	//bi-directional many-to-one association to PurchaseOrder
	@OneToMany(mappedBy="customer")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<PurchaseOrder> purchaseOrders;
	
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
}