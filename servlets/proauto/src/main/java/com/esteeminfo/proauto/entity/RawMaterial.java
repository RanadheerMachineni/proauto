package com.esteeminfo.proauto.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.SortNatural;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * The persistent class for the raw_material database table.
 * 
 */
@Entity
@Table(name="raw_material")
@NamedQuery(name="RawMaterial.findAll", query="SELECT r FROM RawMaterial r")
public class RawMaterial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="raw_material_id")
	private int rawMaterialId;

	@Temporal(TemporalType.DATE)
	private Date doc;

	@Temporal(TemporalType.DATE)
	private Date dou;

	private String desciption;

	private int height;

	private int length;

	@Column(name="number_ofbars")
	private int numberOfbars;

	@Column(name="vendor_id")
	private int vendorId;

	private int width;

	//bi-directional many-to-one association to PurchaseHistory
	@OneToMany(mappedBy="rawMaterial",fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
	@SortNatural
	@OrderBy("adddate ASC")
	private Set<RawMaterialHistory> rawMaterialHistory =  new HashSet<RawMaterialHistory>();
	
	public RawMaterial() {
	}

	public int getRawMaterialId() {
		return this.rawMaterialId;
	}

	public void setRawMaterialId(int rawMaterialId) {
		this.rawMaterialId = rawMaterialId;
	}


	public String getDesciption() {
		return this.desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getLength() {
		return this.length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getNumberOfbars() {
		return this.numberOfbars;
	}

	public void setNumberOfbars(int numberOfbars) {
		this.numberOfbars = numberOfbars;
	}

	public int getVendorId() {
		return this.vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Date getDoc() {
		return doc;
	}

	public void setDoc(Date doc) {
		this.doc = doc;
	}

	public Date getDou() {
		return dou;
	}

	public void setDou(Date dou) {
		this.dou = dou;
	}

	public Set<RawMaterialHistory> getRawMaterialHistory() {
		return rawMaterialHistory;
	}

	public void setRawMaterialHistory(Set<RawMaterialHistory> rawMaterialHistory) {
		this.rawMaterialHistory = rawMaterialHistory;
	}

}