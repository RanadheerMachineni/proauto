package com.esteeminfo.proauto.dto;

public class PurchaseHistoryDTO{
	private String quantity;
	private String date;
	private String authouredby;
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAuthouredby() {
		return authouredby;
	}
	public void setAuthouredby(String authouredby) {
		this.authouredby = authouredby;
	}
}