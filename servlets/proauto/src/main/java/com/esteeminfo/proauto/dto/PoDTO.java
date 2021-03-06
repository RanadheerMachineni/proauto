package com.esteeminfo.proauto.dto;

import java.util.List;

public class PoDTO {
	int pid;
	String poId;
	String version;
	String date;
	String vendor;
	String sender;
	String senderDetails;
	String senderEmail;
	String senderPhone;
	String senderFax;
	String notes;
	String totalValue;
	String customer;
	List<String> material;
	List<String> files;

	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPoId() {
		return poId;
	}
	public void setPoId(String poId) {
		this.poId = poId;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getSenderDetails() {
		return senderDetails;
	}
	public void setSenderDetails(String senderDetails) {
		this.senderDetails = senderDetails;
	}
	public String getSenderEmail() {
		return senderEmail;
	}
	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}
	public String getSenderPhone() {
		return senderPhone;
	}
	public void setSenderPhone(String senderPhone) {
		this.senderPhone = senderPhone;
	}
	public String getSenderFax() {
		return senderFax;
	}
	public void setSenderFax(String senderFax) {
		this.senderFax = senderFax;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(String totalValue) {
		this.totalValue = totalValue;
	}
	public List<String> getMaterial() {
		return material;
	}
	public void setMaterial(List<String> material) {
		this.material = material;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public List<String> getFiles() {
		return files;
	}
	public void setFiles(List<String> files) {
		this.files = files;
	}
}
