package com.esteeminfo.proauto.dto;

import java.util.ArrayList;
import java.util.List;
public class RawMaterialDTO {
	
	private int id;
	private String rawmname;
	private String vendor;
	private String authouredby;
	private String doc;
	private String dou;
	private String quantity;
	private String repository;
	private String length;
	private String width;
	private String thickness;
	List<RawMaterialHistoryDTO> rmHistory = new ArrayList<RawMaterialHistoryDTO>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRawmname() {
		return rawmname;
	}
	public void setRawmname(String rawmname) {
		this.rawmname = rawmname;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getAuthouredby() {
		return authouredby;
	}
	public void setAuthouredby(String authouredby) {
		this.authouredby = authouredby;
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
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getThickness() {
		return thickness;
	}
	public void setThickness(String thickness) {
		this.thickness = thickness;
	}
	public List<RawMaterialHistoryDTO> getRmHistory() {
		return rmHistory;
	}
	public void setRmHistory(List<RawMaterialHistoryDTO> rmHistory) {
		this.rmHistory = rmHistory;
	}
	public String getRepository() {
		return repository;
	}
	public void setRepository(String repository) {
		this.repository = repository;
	}

	

}
