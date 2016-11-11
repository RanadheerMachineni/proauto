package com.esteeminfo.proauto.views;

import java.util.List;

import com.esteeminfo.proauto.entity.Vendor;

public class VendorView {

	private Vendor currentVendor;
	
	private String searchVendorName;
	
	private List<Vendor> listVendors;

	public Vendor getCurrentVendor() {
		return currentVendor;
	}

	public void setCurrentVendor(Vendor currentVendor) {
		this.currentVendor = currentVendor;
	}

	public List<Vendor> getListVendors() {
		return listVendors;
	}

	public void setListVendors(List<Vendor> listVendors) {
		this.listVendors = listVendors;
	}

	public String getSearchVendorName() {
		return searchVendorName;
	}

	public void setSearchVendorName(String searchVendorName) {
		this.searchVendorName = searchVendorName;
	}

}
