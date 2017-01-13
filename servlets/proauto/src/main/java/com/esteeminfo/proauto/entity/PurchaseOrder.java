package com.esteeminfo.proauto.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the purchase_order database table.
 * 
 */
@Entity
@Table(name="purchase_order")
@NamedQuery(name="PurchaseOrder.findAll", query="SELECT p FROM PurchaseOrder p")
public class PurchaseOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int pid;

	@Column(name="total_value")
	private String totalValue;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;

	//bi-directional many-to-one association to PoTool
	@OneToMany(mappedBy="purchaseOrder")
	private Set<PoTool> poTools;

	private String notes;

	@Temporal(TemporalType.DATE)
	private Date pdate;

	@Column(name="po_id")
	private String poId;

	@Column(name="po_version")
	private String poVersion;

	@Column(name="sender_contact")
	private String senderContact;

	@Column(name="sender_details")
	private String senderDetails;

	@Column(name="sender_email")
	private String senderEmail;

	@Column(name="sender_fax")
	private String senderFax;

	@Column(name="sender_phone")
	private String senderPhone;

	@Column(name="vno_sender")
	private String vnoSender;

	@ManyToMany
	@JoinTable(
		name="po_files"
		, joinColumns={
			@JoinColumn(name="pid")
			}
		, inverseJoinColumns={
			@JoinColumn(name="upload_id")
			}
		)
	private Set<FilesUpload> filesUploads;
	
	public PurchaseOrder() {
	}

	public int getPid() {
		return this.pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Date getPdate() {
		return this.pdate;
	}

	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}

	public String getPoId() {
		return this.poId;
	}

	public void setPoId(String poId) {
		this.poId = poId;
	}

	public String getPoVersion() {
		return this.poVersion;
	}

	public void setPoVersion(String poVersion) {
		this.poVersion = poVersion;
	}

	public String getSenderContact() {
		return this.senderContact;
	}

	public void setSenderContact(String senderContact) {
		this.senderContact = senderContact;
	}

	public String getSenderDetails() {
		return this.senderDetails;
	}

	public void setSenderDetails(String senderDetails) {
		this.senderDetails = senderDetails;
	}

	public String getSenderEmail() {
		return this.senderEmail;
	}

	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	public String getSenderFax() {
		return this.senderFax;
	}

	public void setSenderFax(String senderFax) {
		this.senderFax = senderFax;
	}

	public String getSenderPhone() {
		return this.senderPhone;
	}

	public void setSenderPhone(String senderPhone) {
		this.senderPhone = senderPhone;
	}

	public String getVnoSender() {
		return this.vnoSender;
	}

	public void setVnoSender(String vnoSender) {
		this.vnoSender = vnoSender;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Set<PoTool> getPoTools() {
		return this.poTools;
	}

	public void setPoTools(Set<PoTool> poTools) {
		this.poTools = poTools;
	}

	public PoTool addPoTool(PoTool poTool) {
		getPoTools().add(poTool);
		poTool.setPurchaseOrder(this);
		return poTool;
	}

	public PoTool removePoTool(PoTool poTool) {
		getPoTools().remove(poTool);
		poTool.setPurchaseOrder(null);

		return poTool;
	}
	
	public Set<FilesUpload> getFilesUploads() {
		return this.filesUploads;
	}

	public void setFilesUploads(Set<FilesUpload> filesUploads) {
		this.filesUploads = filesUploads;
	}
}