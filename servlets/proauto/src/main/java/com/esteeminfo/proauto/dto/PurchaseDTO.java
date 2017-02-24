package com.esteeminfo.proauto.dto;

import java.util.ArrayList;
import java.util.List;
public class PurchaseDTO {
	
	private int id;
	private String particular;
	private String make;
	private String authouredby;
	private String code;
	private String desciption;
	private String doc;
	private String dou;
	private String quantity;
	private String repository;
	private String tooltypeId;
	private String unit;
	List<PurchaseHistoryDTO> purchaseHistory = new ArrayList<PurchaseHistoryDTO>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getParticular() {
		return particular;
	}
	public void setParticular(String particular) {
		this.particular = particular;
	}

	public String getAuthouredby() {
		return authouredby;
	}
	public void setAuthouredby(String authouredby) {
		this.authouredby = authouredby;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesciption() {
		return desciption;
	}
	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
	}
	public String getDou() {
		return dou;
	}
	public void setDou(String dou) {
		this.dou = dou;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getRepository() {
		return repository;
	}
	public void setRepository(String repository) {
		this.repository = repository;
	}
	public String getTooltypeId() {
		return tooltypeId;
	}
	public void setTooltypeId(String tooltypeId) {
		this.tooltypeId = tooltypeId;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public List<PurchaseHistoryDTO> getPurchaseHistory() {
		return purchaseHistory;
	}
	public void setPurchaseHistory(List<PurchaseHistoryDTO> purchaseHistory) {
		this.purchaseHistory = purchaseHistory;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}

}
