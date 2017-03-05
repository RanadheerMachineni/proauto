package com.esteeminfo.proauto.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.SortNatural;


/**
 * The persistent class for the purchase database table.
 * 
 */
@Entity
@NamedQuery(name="Purchase.findAll", query="SELECT p FROM Purchase p")
public class Purchase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="particular_id")
	private int particularId;

	private String authouredby;

	private String code;

	private String desciption;

	@Temporal(TemporalType.DATE)
	private Date doc;

	@Temporal(TemporalType.DATE)
	private Date dou;

	private String particular;

	private int quantity;

	private int repository;

	@Column(name="tooltype_id")
	private int tooltypeId;


	//bi-directional many-to-one association to Unit
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="unit_id")
	private Unit unit;


	//bi-directional many-to-one association to PurchaseHistory
	@OneToMany(mappedBy="purchase",fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
	@SortNatural
	@OrderBy("adddate ASC")
	private Set<PurchaseHistory> purchaseHistories =  new HashSet<PurchaseHistory>();
	
	//bi-directional many-to-one association to Make
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="make_id")
	private Make make;
	
	public Purchase() {
	}

	public int getParticularId() {
		return this.particularId;
	}

	public void setParticularId(int particularId) {
		this.particularId = particularId;
	}

	public String getAuthouredby() {
		return this.authouredby;
	}

	public void setAuthouredby(String authouredby) {
		this.authouredby = authouredby;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesciption() {
		return this.desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public Date getDoc() {
		return this.doc;
	}

	public void setDoc(Date doc) {
		this.doc = doc;
	}

	public Date getDou() {
		return this.dou;
	}

	public void setDou(Date dou) {
		this.dou = dou;
	}

	public String getParticular() {
		return this.particular;
	}

	public void setParticular(String particular) {
		this.particular = particular;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getRepository() {
		return this.repository;
	}

	public void setRepository(int repository) {
		this.repository = repository;
	}

	public int getTooltypeId() {
		return this.tooltypeId;
	}

	public void setTooltypeId(int tooltypeId) {
		this.tooltypeId = tooltypeId;
	}

	public Set<PurchaseHistory> getPurchaseHistories() {
		return this.purchaseHistories;
	}

	public void setPurchaseHistories(Set<PurchaseHistory> purchaseHistories) {
		this.purchaseHistories = purchaseHistories;
	}

	public PurchaseHistory addPurchaseHistory(PurchaseHistory purchaseHistory) {
		getPurchaseHistories().add(purchaseHistory);
		purchaseHistory.setPurchase(this);

		return purchaseHistory;
	}

	public PurchaseHistory removePurchaseHistory(PurchaseHistory purchaseHistory) {
		getPurchaseHistories().remove(purchaseHistory);
		purchaseHistory.setPurchase(null);

		return purchaseHistory;
	}

	public Make getMake() {
		return this.make;
	}

	public void setMake(Make make) {
		this.make = make;
	}
	
	public Unit getUnit() {
		return this.unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}
}