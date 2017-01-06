package com.esteeminfo.proauto.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


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

	private String discount;

	@Column(name="mat_desc")
	private String matDesc;

	@Column(name="mat_no")
	private String matNo;

	@Column(name="mat_quantiy")
	private int matQuantiy;

	@Column(name="mat_unitprice")
	private String matUnitprice;

	@Column(name="mat_value")
	private String matValue;

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

	public PurchaseOrder() {
	}

	public int getPid() {
		return this.pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getDiscount() {
		return this.discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getMatDesc() {
		return this.matDesc;
	}

	public void setMatDesc(String matDesc) {
		this.matDesc = matDesc;
	}

	public String getMatNo() {
		return this.matNo;
	}

	public void setMatNo(String matNo) {
		this.matNo = matNo;
	}

	public int getMatQuantiy() {
		return this.matQuantiy;
	}

	public void setMatQuantiy(int matQuantiy) {
		this.matQuantiy = matQuantiy;
	}

	public String getMatUnitprice() {
		return this.matUnitprice;
	}

	public void setMatUnitprice(String matUnitprice) {
		this.matUnitprice = matUnitprice;
	}

	public String getMatValue() {
		return this.matValue;
	}

	public void setMatValue(String matValue) {
		this.matValue = matValue;
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

}