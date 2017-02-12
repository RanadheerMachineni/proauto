package com.esteeminfo.proauto.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.SortNatural;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;


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

	private String make;

	private String particular;

	private int quantity;

	private int repository;

	@Column(name="tooltype_id")
	private int tooltypeId;

	private String unit;

	//bi-directional many-to-one association to MachineUsage
	@OneToMany(mappedBy="purchase",fetch=FetchType.LAZY)
	private Set<MachineUsage> machineUsages;

	//bi-directional many-to-one association to PurchaseHistory
	@OneToMany(mappedBy="purchase",fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
	@SortNatural
	@OrderBy("adddate ASC")
	private Set<PurchaseHistory> purchaseHistories =  new HashSet<PurchaseHistory>();
	
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

	public String getMake() {
		return this.make;
	}

	public void setMake(String make) {
		this.make = make;
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

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Set<MachineUsage> getMachineUsages() {
		return this.machineUsages;
	}

	public void setMachineUsages(Set<MachineUsage> machineUsages) {
		this.machineUsages = machineUsages;
	}

	public MachineUsage addMachineUsage(MachineUsage machineUsage) {
		getMachineUsages().add(machineUsage);
		machineUsage.setPurchase(this);

		return machineUsage;
	}

	public MachineUsage removeMachineUsage(MachineUsage machineUsage) {
		getMachineUsages().remove(machineUsage);
		machineUsage.setPurchase(null);

		return machineUsage;
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
}